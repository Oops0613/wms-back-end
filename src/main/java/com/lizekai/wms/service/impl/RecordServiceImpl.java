package com.lizekai.wms.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemConstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.AddApplyDto;
import com.lizekai.wms.domain.dto.ApproveDto;
import com.lizekai.wms.domain.dto.RecordListDto;
import com.lizekai.wms.domain.entity.*;
import com.lizekai.wms.domain.vo.ApplyDetailVo;
import com.lizekai.wms.domain.vo.ExcelRecordVo;
import com.lizekai.wms.domain.vo.PageVo;
import com.lizekai.wms.enums.AppHttpCodeEnum;
import com.lizekai.wms.enums.ApplyTypeEnum;
import com.lizekai.wms.enums.ApproveStatusEnum;
import com.lizekai.wms.handler.exception.SystemException;
import com.lizekai.wms.mapper.RecordMapper;
import com.lizekai.wms.service.*;
import com.lizekai.wms.utils.BeanCopyUtils;
import com.lizekai.wms.utils.SecurityUtils;
import com.lizekai.wms.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
    @Autowired
    private InventoryService inventoryService;

    @Override
    public void refreshVolume() {
        List<Record> recordList = list();
        recordList.forEach(record -> {
            Long goodsId = record.getGoodsId();
            Goods goods = goodsService.getById(goodsId);
            double volume = goods.getVolumePerUnit() * record.getAmount();
            record.setVolume(volume);
            updateById(record);
        });
//        List<Inventory> inventoryList = inventoryService.list();
//        inventoryList.forEach(item -> {
//            Long goodsId=item.getGoodsId();
//            Goods goods=goodsService.getById(goodsId);
//            double volume=goods.getVolumePerUnit()*item.getAmount();
//            item.setVolume(volume);
//            inventoryService.updateById(item);
//        });
    }

    @Override
    public ResponseResult getRecordList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        return getApplyListByType(dto, pageNum, pageSize, dto.getType());
    }

    @Override
    public ResponseResult getInApplyList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        return getApplyListByType(dto, pageNum, pageSize, ApplyTypeEnum.IN_APPLY.getCode());
    }

    @Override
    public ResponseResult getOutApplyList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        return getApplyListByType(dto, pageNum, pageSize, ApplyTypeEnum.OUT_APPLY.getCode());
    }


    @Override
    public ResponseResult getAllotApplyList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        return getApplyListByType(dto, pageNum, pageSize, ApplyTypeEnum.ALLOT_APPLY.getCode());
    }

    /**
     * 按申请类型获取列表
     *
     * @param dto  查询条件：categoryId分类 goodsName货物名模糊查询 fromId源仓库 toId目的仓库
     * @param type （1入库2出库3调拨）其余值=全量查询
     */
    private ResponseResult getApplyListByType(RecordListDto dto, Integer pageNum, Integer pageSize, String type) {
        LambdaQueryWrapper<Record> wrapper = new LambdaQueryWrapper<>();
        Long fromId = dto.getFromId();
        Long toId = dto.getToId();
        Long categoryId = dto.getCategoryId();
        String goodsName = dto.getGoodsName();
        String approveStatus = dto.getApproveStatus();
        wrapper.eq(Objects.nonNull(fromId) && !ApplyTypeEnum.IN_APPLY.getCode().equals(type), Record::getFromId, fromId);
        wrapper.eq(Objects.nonNull(toId) && !ApplyTypeEnum.OUT_APPLY.getCode().equals(type), Record::getToId, toId);
        if (ApplyTypeEnum.IN_APPLY.getCode().equals(type)) {
            wrapper.eq(Record::getFromId, -1);
        } else if (ApplyTypeEnum.OUT_APPLY.getCode().equals(type)) {
            wrapper.eq(Record::getToId, -1);
        } else if (ApplyTypeEnum.ALLOT_APPLY.getCode().equals(type)) {
            wrapper.gt(Record::getFromId, 0);
            wrapper.gt(Record::getToId, 0);
        }
        wrapper.eq(Objects.nonNull(categoryId), Record::getCategoryId, categoryId);
        wrapper.eq(StringUtils.hasText(approveStatus), Record::getApproveStatus, approveStatus);
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
            vo.setUnit(goods.getUnit());
        }
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult addInApply(AddApplyDto dto) {
        return addApplyByType(dto, ApplyTypeEnum.IN_APPLY.getCode());
    }

    @Override
    public ResponseResult addOutApply(AddApplyDto dto) {
        return addApplyByType(dto, ApplyTypeEnum.OUT_APPLY.getCode());
    }

    @Override
    public ResponseResult addAllotApply(AddApplyDto dto) {
        return addApplyByType(dto, ApplyTypeEnum.ALLOT_APPLY.getCode());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult approvePass(ApproveDto dto) {
        //申请类型
        String type = dto.getType();
        Inventory inventory = new Inventory();
        Record record = getById(dto.getId());
        if (ApplyTypeEnum.IN_APPLY.getCode().equals(type)) {
            Warehouse warehouse = warehouseService.getById(record.getToId());
            Double remainingCapacity = warehouse.getRemainingCapacity();
            Goods goods = goodsService.getById(record.getGoodsId());
            double volume = goods.getVolumePerUnit() * record.getAmount();
            if (remainingCapacity < volume) {
                throw new SystemException(AppHttpCodeEnum.OUT_OF_CAPACITY);
            }
            //检查入库货物是否过期
            if (SystemConstants.CAN_EXPIRE.equals(record.getHasExpirationTime())
                    && record.getExpirationTime().before(new Date())) {
                throw new SystemException(AppHttpCodeEnum.GOODS_EXPIRED);
            }
            inventory.setGoodsId(record.getGoodsId());
            inventory.setGoodsName(record.getGoodsName());
            inventory.setWarehouseId(record.getToId());
            inventory.setWarehouseName(record.getToName());
            inventory.setCategoryId(record.getCategoryId());
            inventory.setCategoryName(record.getCategoryName());
            inventory.setAmount(record.getAmount());
            inventory.setVolume(volume);
            inventory.setEnterTime(new Date());
            inventory.setHasExpirationTime(record.getHasExpirationTime());
            inventory.setExpirationTime(record.getExpirationTime());
            inventoryService.save(inventory);
            //获取新增的库存ID
            record.setInventoryId(inventory.getId());
            //更新仓库剩余容量
            warehouse.setRemainingCapacity(remainingCapacity - volume);
            warehouseService.updateById(warehouse);
            //更新库存总数
            goods.setAmount(goods.getAmount() + record.getAmount());
            goodsService.updateById(goods);
        } else if (ApplyTypeEnum.OUT_APPLY.getCode().equals(type)) {
            //检查该笔库存数量是否足够
            inventory = inventoryService.getById(record.getInventoryId());
            if (inventory.getAmount() < record.getAmount()) {
                throw new SystemException(AppHttpCodeEnum.INVENTORY_INSUFFICIENT);
            }
            //更新仓库剩余容量
            Goods goods = goodsService.getById(record.getGoodsId());
            Warehouse warehouse = warehouseService.getById(record.getFromId());
            Double remainingCapacity = warehouse.getRemainingCapacity();
            double volume = goods.getVolumePerUnit() * record.getAmount();
            warehouse.setRemainingCapacity(remainingCapacity + volume);
            warehouseService.updateById(warehouse);
            //更改库存数量和容量
            inventory.setAmount(inventory.getAmount() - record.getAmount());
            inventory.setVolume(inventory.getVolume() - volume);
            inventoryService.updateById(inventory);
            //更新库存总数
            goods.setAmount(goods.getAmount() - record.getAmount());
            goodsService.updateById(goods);
        } else if (ApplyTypeEnum.ALLOT_APPLY.getCode().equals(type)) {
            //检查目的仓库剩余容量是否足够
            Warehouse warehouse = warehouseService.getById(record.getToId());
            Double remainingCapacity = warehouse.getRemainingCapacity();
            Goods goods = goodsService.getById(record.getGoodsId());
            double volume = goods.getVolumePerUnit() * record.getAmount();
            if (remainingCapacity < volume) {
                throw new SystemException(AppHttpCodeEnum.OUT_OF_CAPACITY);
            }
            //检查该笔库存数量是否足够
            inventory = inventoryService.getById(record.getInventoryId());
            if (inventory.getAmount() < record.getAmount()) {
                throw new SystemException(AppHttpCodeEnum.INVENTORY_INSUFFICIENT);
            }
            //更改旧库存数量和容量
            inventory.setAmount(inventory.getAmount() - record.getAmount());
            inventory.setVolume(inventory.getVolume() - volume);
            inventoryService.updateById(inventory);
            //增加一笔新的库存
            Inventory newInventory = new Inventory();
            newInventory.setGoodsId(record.getGoodsId());
            newInventory.setGoodsName(record.getGoodsName());
            newInventory.setWarehouseId(record.getToId());
            newInventory.setWarehouseName(record.getToName());
            newInventory.setCategoryId(record.getCategoryId());
            newInventory.setCategoryName(record.getCategoryName());
            newInventory.setAmount(record.getAmount());
            newInventory.setVolume(volume);
            newInventory.setEnterTime(new Date());
            newInventory.setHasExpirationTime(record.getHasExpirationTime());
            newInventory.setExpirationTime(record.getExpirationTime());
            inventoryService.save(newInventory);
            //获取新增的库存ID
            record.setNewInventoryId(newInventory.getId());
            //更新仓库剩余容量
            warehouse.setRemainingCapacity(remainingCapacity - volume);
            warehouseService.updateById(warehouse);
            Warehouse oldWarehouse = warehouseService.getById(record.getFromId());
            oldWarehouse.setRemainingCapacity(oldWarehouse.getRemainingCapacity() + volume);
            warehouseService.updateById(oldWarehouse);
        } else {
            //type值异常
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, "申请类型不存在");
        }
        //更新Record表
        User approveUser = SecurityUtils.getLoginUser().getUser();
        record.setApproveBy(approveUser.getId());
        record.setApproveRemark(dto.getApproveRemark());
        record.setApproveStatus(ApproveStatusEnum.APPROVE_PASS.getCode());
        record.setApproveTime(new Date());
        updateById(record);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult approveReject(ApproveDto dto) {
        Record record = getById(dto.getId());
        User approveUser = SecurityUtils.getLoginUser().getUser();
        record.setApproveBy(approveUser.getId());
        record.setApproveRemark(dto.getApproveRemark());
        record.setApproveStatus(ApproveStatusEnum.APPROVE_REJECT.getCode());
        record.setApproveTime(new Date());
        updateById(record);
        return ResponseResult.okResult();
    }

    /**
     * 在正式审批之前使用，将未审批和无法审批的申请更正状态
     */
    @Override
    public ResponseResult preApprove() {
        //获取状态为未审批or无法审批的申请
        LambdaQueryWrapper<Record> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Record::getApproveStatus,
                Arrays.asList(ApproveStatusEnum.NEED_APPROVE.getCode(),
                        ApproveStatusEnum.APPROVE_UNAVAILABLE.getCode()));
        List<Record> recordList = list(wrapper);
        //遍历列表，检查条件
        recordList.forEach(record -> {
            boolean valid = checkStatus(record);
            String oldStatus = record.getApproveStatus();
            //未审批->无法审批
            if (ApproveStatusEnum.NEED_APPROVE.getCode().equals(oldStatus) && !valid) {
                record.setApproveStatus(ApproveStatusEnum.APPROVE_UNAVAILABLE.getCode());
                updateById(record);
            }
            //无法审批->未审批
            if (ApproveStatusEnum.APPROVE_UNAVAILABLE.getCode().equals(oldStatus) && valid) {
                record.setApproveStatus(ApproveStatusEnum.NEED_APPROVE.getCode());
                updateById(record);
            }
        });
        return ResponseResult.okResult();
    }

    @Override
    public void export(HttpServletResponse response) {
        try {
            //设置下载文件的请求头（文件名）
            String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            WebUtils.setDownLoadHeader("出入库记录_" + currentTime + ".xlsx", response);
            //获取需要导出的数据
            List<Record> recordList = list();
            List<ExcelRecordVo> excelRecordVos = BeanCopyUtils.copyBeanList(recordList, ExcelRecordVo.class);
            //把用户ID替换为用户名
            Map<Long, String> userMap = userService.list().stream()
                    .collect(Collectors.toMap(User::getId, User::getRealName));
            excelRecordVos.forEach(item -> {
                item.setApplyByName(userMap.get(item.getApplyBy()));
                item.setApproveByName(userMap.get(item.getApproveBy()));
                if (!SystemConstants.CAN_EXPIRE.equals(item.getHasExpirationTime())) {
                    item.setExpirationTime(null);
                }
                if(item.getFromId()<0){
                    item.setFromId(null);
                }
                if(item.getToId()<0){
                    item.setToId(null);
                }
                //填充申请类型字段
                String type = item.getType();
                ApplyTypeEnum applyEnum=ApplyTypeEnum.getApplyTypeByCode(type);
                if(Objects.nonNull(applyEnum)){
                    item.setTypeName(applyEnum.getMsg());
                }
                //填充审批状态字段
                String status = item.getApproveStatus();
                ApproveStatusEnum approveEnum=ApproveStatusEnum.getApproveStatusByCode(status);
                if(Objects.nonNull(approveEnum)){
                    item.setApproveStatusName(approveEnum.getMsg());
                }
            });
            //把数据写入到Excel中
            //sheet方法里面的字符串是Excel表格左下角工作簿的名字
            EasyExcel.write(response.getOutputStream(), ExcelRecordVo.class)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("出入库记录")
                    .doWrite(excelRecordVos);
        } catch (Exception e) {
            //如果出现异常,就返回失败的json数据给前端。
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
            //将json字符串写入到请求体，然后返回给前端
            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    /**
     * 检查出入库申请是否能通过预审批
     *
     * @return true表示通过
     */
    private boolean checkStatus(Record record) {
        String type = record.getType();
        if (ApplyTypeEnum.IN_APPLY.getCode().equals(type)) {
            //检查目的仓库剩余容量是否足够
            if (!checkRemainingCapacity(record)) {
                record.setApproveRemark("目的仓库剩余容量不足");
                return false;
            }
            //检查入库货物是否过期
            if (SystemConstants.CAN_EXPIRE.equals(record.getHasExpirationTime())
                    && record.getExpirationTime().before(new Date())) {
                record.setApproveRemark("入库货物过期");
                return false;
            }
        } else if (ApplyTypeEnum.OUT_APPLY.getCode().equals(type)) {
            //检查该笔库存数量是否足够
            Inventory inventory = inventoryService.getById(record.getInventoryId());
            if (inventory.getAmount() < record.getAmount()) {
                record.setApproveRemark("该笔库存数量不足");
                return false;
            }
        } else if (ApplyTypeEnum.ALLOT_APPLY.getCode().equals(type)) {
            //检查目的仓库剩余容量是否足够
            if (!checkRemainingCapacity(record)) {
                record.setApproveRemark("目的仓库剩余容量不足");
                return false;
            }
            //检查该笔库存数量是否足够
            Inventory inventory = inventoryService.getById(record.getInventoryId());
            if (inventory.getAmount() < record.getAmount()) {
                record.setApproveRemark("该笔库存数量不足");
                return false;
            }
        }
        //可以审批，则清除审批备注
        record.setApproveRemark("");
        return true;
    }

    /**
     * 检查目的仓库剩余容量是否足够
     *
     * @return 足够=true
     */
    private boolean checkRemainingCapacity(Record record) {
        Warehouse warehouse = warehouseService.getById(record.getToId());
        Double remainingCapacity = warehouse.getRemainingCapacity();
        Goods goods = goodsService.getById(record.getGoodsId());
        double volume = goods.getVolumePerUnit() * record.getAmount();
        return remainingCapacity >= volume;
    }

    /**
     * 按类型新增申请
     *
     * @param type （1入库2出库3调拨）
     */
    private ResponseResult addApplyByType(AddApplyDto dto, String type) {
        Goods goods = goodsService.getById(dto.getGoodsId());
        User user = SecurityUtils.getLoginUser().getUser();
        Warehouse fromWarehouse = warehouseService.getById(dto.getFromId());
        Warehouse toWarehouse = warehouseService.getById(dto.getToId());
        Record record = new Record();
        if (ApplyTypeEnum.IN_APPLY.getCode().equals(type)) {
            record.setFromId(-1L);
            record.setToId(toWarehouse.getId());
            record.setToName(toWarehouse.getName());
        } else if (ApplyTypeEnum.OUT_APPLY.getCode().equals(type)) {
            record.setFromId(fromWarehouse.getId());
            record.setFromName(fromWarehouse.getName());
            record.setToId(-1L);
            record.setInventoryId(dto.getInventoryId());
        } else if (ApplyTypeEnum.ALLOT_APPLY.getCode().equals(type)) {
            record.setFromId(fromWarehouse.getId());
            record.setFromName(fromWarehouse.getName());
            record.setToId(toWarehouse.getId());
            record.setToName(toWarehouse.getName());
            record.setInventoryId(dto.getInventoryId());
        } else {
            //type值异常
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR, "申请类型不存在");
        }
        record.setCategoryId(goods.getCategoryId());
        record.setCategoryName(goods.getCategoryName());
        record.setGoodsId(goods.getId());
        record.setGoodsName(goods.getName());
        record.setHasExpirationTime(goods.getHasExpirationTime());
        //货物有过期时间
        if (SystemConstants.CAN_EXPIRE.equals(goods.getHasExpirationTime())) {
            record.setExpirationTime(dto.getExpirationTime());
        }
        record.setAmount(dto.getAmount());
        record.setVolume(dto.getAmount() * goods.getVolumePerUnit());
        record.setApplyBy(user.getId());
        record.setApplyRemark(dto.getApplyRemark());
        record.setApplyTime(new Date());
        record.setType(type);
        save(record);
        return ResponseResult.okResult();
    }
}

