package com.lizekai.wms.controller;

import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.InventoryListDto;
import com.lizekai.wms.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseResult getInventoryList(InventoryListDto dto, Integer pageNum, Integer pageSize){
        return inventoryService.getInventoryList(dto,pageNum,pageSize);
    }
    @GetMapping("/export")
    //注意返回值类型是void
    public void export(HttpServletResponse response){
        inventoryService.export(response);
    }
}
