package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.GetSalesCompositionDto;
import com.lizekai.wms.domain.dto.GetWarehouseCompositionDto;
import com.lizekai.wms.domain.entity.Inventory;
import org.springframework.stereotype.Service;

@Service
public interface CategoryStatService extends IService<Inventory> {
    ResponseResult getWarehouseComposition(GetWarehouseCompositionDto warehouseId);
    ResponseResult getSalesComposition(GetSalesCompositionDto dto);
}
