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
        LambdaQueryWrapper<Record> wrapper=new LambdaQueryWrapper<>();
        Long fromId=dto.getFromId();
        Long toId=dto.getToId();
        String type=dto.getType();
        wrapper.eq(Objects.nonNull(fromId),Record::getFromId,fromId);
        wrapper.eq(Objects.nonNull(toId),Record::getToId,toId);
        if(StringUtils.hasText(type)){
            if(SystemCanstants.IN_APPLY.equals(type)){
                wrapper.eq(Record::getFromId,-1);
            }
            else if(SystemCanstants.OUT_APPLY.equals(type)){
                wrapper.eq(Record::getToId,-1);
            }
            else if(SystemCanstants.ALLOT_APPLY.equals(type)){
                wrapper.gt(Record::getFromId,0);
                wrapper.gt(Record::getToId,0);
            }
        }
        Page<Record> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult getInApplyList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        Long toId=dto.getToId();
        Long categoryId=dto.getCategoryId();
        String goodsName=dto.getGoodsName();
        LambdaQueryWrapper<Record> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Record::getFromId,-1);
        wrapper.eq(Objects.nonNull(toId),Record::getToId,toId);
        wrapper.eq(Objects.nonNull(categoryId),Record::getCategoryId,categoryId);
        wrapper.like(StringUtils.hasText(goodsName),Record::getGoodsName,goodsName);
        Page<Record> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult getOutApplyList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        String goodsName=dto.getGoodsName();
        Long fromId=dto.getFromId();
        Long categoryId=dto.getCategoryId();
        LambdaQueryWrapper<Record> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(fromId),Record::getFromId,fromId);
        wrapper.eq(Record::getToId,-1);
        wrapper.eq(Objects.nonNull(categoryId),Record::getCategoryId,categoryId);
        wrapper.like(StringUtils.hasText(goodsName),Record::getGoodsName,goodsName);
        Page<Record> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult getAllotApplyList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        Long toId=dto.getToId();
        Long fromId=dto.getFromId();
        Long categoryId=dto.getCategoryId();
        String goodsName=dto.getGoodsName();
        LambdaQueryWrapper<Record> wrapper=new LambdaQueryWrapper<>();
        wrapper.gt(Record::getFromId,0);
        wrapper.gt(Record::getToId,0);
        wrapper.eq(Objects.nonNull(fromId),Record::getFromId,fromId);
        wrapper.eq(Objects.nonNull(toId),Record::getToId,toId);
        wrapper.eq(Objects.nonNull(categoryId),Record::getCategoryId,categoryId);
        wrapper.like(StringUtils.hasText(goodsName),Record::getGoodsName,goodsName);
        Page<Record> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult getApplyById(Long id) {
        Record record=getById(id);
        ApplyDetailVo vo = BeanCopyUtils.copyBean(record, ApplyDetailVo.class);
        User applyUser = userService.getById(record.getApplyBy());
        User approveUser = userService.getById(record.getApproveBy());
        Goods goods =goodsService.getById(record.getGoodsId());
        if(Objects.nonNull(applyUser)){
            vo.setApplyUserName(applyUser.getRealName());
        }
        if(Objects.nonNull(approveUser)){
            vo.setApproveUserName(approveUser.getRealName());
        }
        if(Objects.nonNull(goods)){
            vo.setVolume(record.getAmount()*goods.getVolumePerUnit());
        }
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult addInApply(AddApplyDto dto) {
        Goods goods = goodsService.getById(dto.getGoodsId());
        User user = SecurityUtils.getLoginUser().getUser();
        Warehouse warehouse = warehouseService.getById(dto.getToId());
        Record record=new Record();
        record.setFromId(-1L);
        record.setToId(warehouse.getId());
        record.setToName(warehouse.getName());
        record.setCategoryId(goods.getCategoryId());
        record.setCategoryName(goods.getCategoryName());
        record.setGoodsId(goods.getId());
        record.setGoodsName(goods.getName());
        record.setHasExpirationTime(goods.getHasExpirationTime());
        //货物有过期时间
        if(SystemCanstants.CAN_EXPIRE.equals(goods.getHasExpirationTime())){
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

