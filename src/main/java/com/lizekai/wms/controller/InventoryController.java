package com.lizekai.wms.controller;

import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.InventoryListDto;
import com.lizekai.wms.domain.entity.Inventory;
import com.lizekai.wms.domain.entity.Inventory;
import com.lizekai.wms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/list")
    public ResponseResult getInventoryList(InventoryListDto dto, Integer pageNum, Integer pageSize){
        return inventoryService.getInventoryList(dto,pageNum,pageSize);
    }
}
