package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemCanstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.AddApplyDto;
import com.lizekai.wms.domain.dto.RecordListDto;
import com.lizekai.wms.domain.entity.*;
import com.lizekai.wms.domain.vo.ApplyDetailVo;
import com.lizekai.wms.domain.vo.PageVo;
import com.lizekai.wms.enums.AppHttpCodeEnum;
import com.lizekai.wms.mapper.RecordMapper;
import com.lizekai.wms.service.GoodsService;
import com.lizekai.wms.service.RecordService;
import com.lizekai.wms.service.UserService;
import com.lizekai.wms.service.WarehouseService;
import com.lizekai.wms.utils.BeanCopyUtils;
import com.lizekai.wms.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * 出入库记录表(Record)表服务实现类
 *
 * @author makejava
 * @since 2025-01-13 10:31:58
 */
@Service("recordService")
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private WarehouseService warehouseService;

    @Override
    public ResponseResult getRecordList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        return getApplyListByType(dto,pageNum,pageSize,"");
    }

    @Override
    public ResponseResult getInApplyList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        return getApplyListByType(dto,pageNum,pageSize,SystemCanstants.IN_APPLY);
    }

    @Override
    public ResponseResult getOutApplyList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        return getApplyListByType(dto,pageNum,pageSize,SystemCanstants.OUT_APPLY);
    }


    @Override
    public ResponseResult getAllotApplyList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        return getApplyListByType(dto,pageNum,pageSize,SystemCanstants.ALLOT_APPLY);
    }

    /**
     * 按申请类型获取列表
     * @param dto 查询条件：categoryId分类 goodsName货物名模糊查询 fromId源仓库 toId目的仓库
     * @param type （1入库2出库3调拨）其余值=全量查询
     */
    private ResponseResult getApplyListByType(RecordListDto dto, Integer pageNum, Integer pageSize, String type) {
        LambdaQueryWrapper<Record> wrapper = new LambdaQueryWrapper<>();
        Long fromId = dto.getFromId();
        Long toId = dto.getToId();
        Long categoryId = dto.getCategoryId();
        String goodsName = dto.getGoodsName();
        String approveStatus= dto.getApproveStatus();
        wrapper.eq(Objects.nonNull(fromId), Record::getFromId, fromId);
        wrapper.eq(Objects.nonNull(toId), Record::getToId, toId);
        if (SystemCanstants.IN_APPLY.equals(type)) {
            wrapper.eq(Record::getFromId, -1);
        } else if (SystemCanstants.OUT_APPLY.equals(type)) {
            wrapper.eq(Record::getToId, -1);
        } else if (SystemCanstants.ALLOT_APPLY.equals(type)) {
            wrapper.gt(Record::getFromId, 0);
            wrapper.gt(Record::getToId, 0);
        }
        wrapper.eq(Objects.nonNull(categoryId), Record::getCategoryId, categoryId);
        wrapper.eq(StringUtils.hasText(approveStatus),Record::getApproveStatus,approveStatus);
        wrapper.like(StringUtils.hasText(goodsName), Record::getGoodsName, goodsName);
        Page<Record> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult getApplyById(Long id) {
        Record record = getById(id);
        ApplyDetailVo vo = BeanCopyUtils.copyBean(record, ApplyDetailVo.class);
        User applyUser = userService.getById(record.getApplyBy());
        User approveUser = userService.getById(record.getApproveBy());
        Goods goods = goodsService.getById(record.getGoodsId());
        if (Objects.nonNull(applyUser)) {
            vo.setApplyUserName(applyUser.getRealName());
        }
        if (Objects.nonNull(approveUser)) {
            vo.setApproveUserName(approveUser.getRealName());
        }
        if (Objects.nonNull(goods)) {
            vo.setVolume(record.getAmount() * goods.getVolumePerUnit());
        }
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult addInApply(AddApplyDto dto) {
        return addApplyByType(dto,SystemCanstants.IN_APPLY);
    }

    @Override
    public ResponseResult addOutApply(AddApplyDto dto) {
        return addApplyByType(dto,SystemCanstants.OUT_APPLY);
    }

    @Override
    public ResponseResult addAllotApply(AddApplyDto dto) {
        return addApplyByType(dto,SystemCanstants.ALLOT_APPLY);
    }

    /**
     * 按类型新增申请
     * @param type （1入库2出库3调拨）
     */
    private ResponseResult addApplyByType(AddApplyDto dto,String type){
        Goods goods = goodsService.getById(dto.getGoodsId());
        User user = SecurityUtils.getLoginUser().getUser();
        Warehouse fromWarehouse = warehouseService.getById(dto.getFromId());
        Warehouse toWarehouse = warehouseService.getById(dto.getToId());
        Record record = new Record();
        if(SystemCanstants.IN_APPLY.equals(type)){
            record.setFromId(-1L);
            record.setToId(toWarehouse.getId());
            record.setToName(toWarehouse.getName());
        }else if(SystemCanstants.OUT_APPLY.equals(type)){
            record.setFromId(fromWarehouse.getId());
            record.setFromName(fromWarehouse.getName());
            record.setToId(-1L);
            record.setInventoryId(dto.getInventoryId());
        }else if(SystemCanstants.ALLOT_APPLY.equals(type)){
            record.setFromId(fromWarehouse.getId());
            record.setFromName(fromWarehouse.getName());
            record.setToId(toWarehouse.getId());
            record.setToName(toWarehouse.getName());
            record.setInventoryId(dto.getInventoryId());
        }else {
            //type值异常
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"申请类型不存在");
        }
        record.setCategoryId(goods.getCategoryId());
        record.setCategoryName(goods.getCategoryName());
        record.setGoodsId(goods.getId());
        record.setGoodsName(goods.getName());
        record.setHasExpirationTime(goods.getHasExpirationTime());
        //货物有过期时间
        if (SystemCanstants.CAN_EXPIRE.equals(goods.getHasExpirationTime())) {
            record.setExpirationTime(dto.getExpirationTime());
        }
        record.setAmount(dto.getAmount());
        record.setApplyBy(user.getId());
        record.setApplyRemark(dto.getApplyRemark());
        record.setApplyTime(new Date());
        save(record);
        return ResponseResult.okResult();
    }
}

