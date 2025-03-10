package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.Warehouse;
import com.lizekai.wms.domain.vo.PageVo;
import com.lizekai.wms.enums.AppHttpCodeEnum;
import com.lizekai.wms.handler.exception.SystemException;
import com.lizekai.wms.mapper.WarehouseMapper;
import com.lizekai.wms.service.WarehouseService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 仓库表(Warehouse)表服务实现类
 *
 * @author makejava
 * @since 2024-12-22 13:23:17
 */
@Service("warehouseService")
public class WarehouseServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseService {

    @Override
    public ResponseResult getWarehouseList(Warehouse warehouse, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Warehouse> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(warehouse.getName()), Warehouse::getName, warehouse.getName());

        Page<Warehouse> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult addWarehouse(Warehouse warehouse) {
        if(existWarehouseName(warehouse.getName())){
            throw new SystemException(AppHttpCodeEnum.WAREHOUSE_NAME_EXIST);
        }
        warehouse.setRemainingCapacity(warehouse.getCapacity());
        save(warehouse);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateWarehouse(Warehouse warehouse) {
        Warehouse old = getById(warehouse.getId());
        if (old.getRemainingCapacity() < old.getCapacity()) {
            return ResponseResult.errorResult(500, "该仓库正在使用中，无法修改");
        }
        Warehouse oldWarehouse = getById(warehouse.getId());
        if(!oldWarehouse.getName().equals(warehouse.getName())){
            if(existWarehouseName(warehouse.getName())){
                throw new SystemException(AppHttpCodeEnum.WAREHOUSE_NAME_EXIST);
            }
        }
        warehouse.setRemainingCapacity(warehouse.getCapacity());
        updateById(warehouse);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getWarehouseById(Long id) {
        return ResponseResult.okResult(getById(id));
    }

    @Override
    public ResponseResult deleteWarehouse(Long id) {
        Warehouse warehouse = getById(id);
        if (warehouse.getRemainingCapacity() < warehouse.getCapacity()) {
            return ResponseResult.errorResult(500, "该仓库正在使用中，无法删除");
        }
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult listAllWarehouse() {
        LambdaQueryWrapper<Warehouse> wrapper = new LambdaQueryWrapper<>();
        return ResponseResult.okResult(list(wrapper));
    }
    private boolean existWarehouseName(String name){
        LambdaQueryWrapper<Warehouse> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Warehouse::getName,name);
        return count(wrapper)>0;
    }
}

