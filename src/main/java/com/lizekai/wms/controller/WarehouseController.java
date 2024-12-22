package com.lizekai.wms.controller;

import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.Warehouse;
import com.lizekai.wms.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    @GetMapping("/list")
    public ResponseResult getWarehouseList(Warehouse warehouse, Integer pageNum, Integer pageSize){
        return warehouseService.getWarehouseList(warehouse,pageNum,pageSize);
    }
    @PostMapping
    public ResponseResult addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }
    @DeleteMapping("/{id}")
    public ResponseResult deleteWarehouse(@PathVariable(name = "id") Long id){
        return warehouseService.deleteWarehouse(id);
    }
    @PutMapping()
    public ResponseResult updateWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.updateWarehouse(warehouse);
    }
    @GetMapping("/{id}")
    public ResponseResult getWarehouseById(@PathVariable("id") Long id){
        return warehouseService.getWarehouseById(id);
    }
}
