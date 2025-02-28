package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemCanstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.InventoryListDto;
import com.lizekai.wms.domain.entity.Goods;
import com.lizekai.wms.domain.vo.PageVo;
import com.lizekai.wms.mapper.InventoryMapper;
import com.lizekai.wms.domain.entity.Inventory;
import com.lizekai.wms.service.InventoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 库存表(Inventory)表服务实现类
 *
 * @author makejava
 * @since 2025-01-13 10:25:55
 */
@Service("inventoryService")
public class InventoryServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements InventoryService {

    @Override
    public ResponseResult getInventoryList(InventoryListDto dto, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Inventory> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(dto.getWarehouseId()),Inventory::getWarehouseId,dto.getWarehouseId());
        wrapper.eq(Objects.nonNull(dto.getCategoryId()),Inventory::getCategoryId,dto.getCategoryId());
        wrapper.like(StringUtils.hasText(dto.getGoodsName()),Inventory::getGoodsName,dto.getGoodsName());
        //需要忽略为0的库存
        if(SystemCanstants.IGNORE_ZERO.equals(dto.getIgnoreZero())){
            wrapper.gt(Inventory::getAmount,0);
        }
        Page<Inventory> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }
}

