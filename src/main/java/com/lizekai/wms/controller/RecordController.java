package com.lizekai.wms.controller;

import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.InventoryListDto;
import com.lizekai.wms.domain.dto.RecordListDto;
import com.lizekai.wms.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;
    @GetMapping("/list")
    public ResponseResult getRecordList(RecordListDto dto, Integer pageNum, Integer pageSize){
        return recordService.getRecordList(dto,pageNum,pageSize);
    }
    @GetMapping("/listInApply")
    public ResponseResult getInApplyList(RecordListDto dto, Integer pageNum, Integer pageSize){
        return recordService.getInApplyList(dto,pageNum,pageSize);
    }
    @GetMapping("/listOutApply")
    public ResponseResult getOutApplyList(RecordListDto dto, Integer pageNum, Integer pageSize){
        return recordService.getOutApplyList(dto,pageNum,pageSize);
    }
    @GetMapping("/listAllotApply")
    public ResponseResult getAllotApplyList(RecordListDto dto, Integer pageNum, Integer pageSize){
        return recordService.getAllotApplyList(dto,pageNum,pageSize);
    }
}
