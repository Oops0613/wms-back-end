package com.lizekai.wms.controller;

import com.lizekai.wms.annotation.SystemLog;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.InventoryListDto;
import com.lizekai.wms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/list")
    @SystemLog(businessName = "分页查询库存列表")
    public ResponseResult getInventoryList(InventoryListDto dto, Integer pageNum, Integer pageSize){
        return inventoryService.getInventoryList(dto,pageNum,pageSize);
    }
    @GetMapping("/export")
    @PreAuthorize("@ps.hasPermission('inventory:export')")
    @SystemLog(businessName = "导出所有库存列表")
    //注意返回值类型是void
    public void export(HttpServletResponse response){
        inventoryService.export(response);
    }
}
