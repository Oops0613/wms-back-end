package com.lizekai.wms.controller;

import com.lizekai.wms.annotation.SystemLog;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.GetLoadRateDto;
import com.lizekai.wms.domain.entity.Warehouse;
import com.lizekai.wms.service.WarehouseService;
import com.lizekai.wms.service.WarehouseStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private WarehouseStatService warehouseStatService;

    @GetMapping("/listAll")
    @SystemLog(businessName = "查询所有仓库列表")
    public ResponseResult listAllWarehouse(){
        return warehouseService.listAllWarehouse();
    }
    @GetMapping("/list")
    @SystemLog(businessName = "分页查询仓库列表")
    public ResponseResult getWarehouseList(Warehouse warehouse, Integer pageNum, Integer pageSize){
        return warehouseService.getWarehouseList(warehouse,pageNum,pageSize);
    }
    @PostMapping
    @SystemLog(businessName = "新增仓库")
    public ResponseResult addWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.addWarehouse(warehouse);
    }
    @DeleteMapping("/{id}")
    @SystemLog(businessName = "删除仓库")
    public ResponseResult deleteWarehouse(@PathVariable(name = "id") Long id){
        return warehouseService.deleteWarehouse(id);
    }
    @PutMapping()
    @SystemLog(businessName = "修改仓库")
    public ResponseResult updateWarehouse(@RequestBody Warehouse warehouse){
        return warehouseService.updateWarehouse(warehouse);
    }
    @GetMapping("/{id}")
    @SystemLog(businessName = "查询仓库信息")
    public ResponseResult getWarehouseById(@PathVariable("id") Long id){
        return warehouseService.getWarehouseById(id);
    }
    @GetMapping("/getLoadRate")
    @SystemLog(businessName = "查询仓库负载日变化")
    public ResponseResult getLoadRate(GetLoadRateDto dto){
        return warehouseStatService.GetLoadRate(dto);
    }
    @GetMapping("/listWarehouseByLoadRate/{bound}")
    @SystemLog(businessName = "查询仓库预警信息列表")
    public ResponseResult listWarehouseByLoadRate(@PathVariable("bound") Double bound){
        return warehouseStatService.listWarehouseByLoadRate(bound);
    }
}
