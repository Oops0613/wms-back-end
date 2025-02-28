package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.GetLoadRateDto;
import com.lizekai.wms.domain.entity.Warehouse;
import com.lizekai.wms.domain.entity.Warehouse;
import org.springframework.stereotype.Service;

/**
 * 仓库表(Warehouse)表服务接口
 *
 * @author makejava
 * @since 2024-12-22 13:23:17
 */
@Service
public interface WarehouseService extends IService<Warehouse> {
    ResponseResult getWarehouseList(Warehouse warehouse, Integer pageNum, Integer pageSize);
    ResponseResult addWarehouse(Warehouse warehouse);

    ResponseResult updateWarehouse(Warehouse warehouse);

    ResponseResult getWarehouseById(Long id);

    ResponseResult deleteWarehouse(Long id);

    ResponseResult listAllWarehouse();
}

