package com.lizekai.wms.controller;

import com.lizekai.wms.annotation.SystemLog;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.AddApplyDto;
import com.lizekai.wms.domain.dto.ApproveDto;
import com.lizekai.wms.domain.dto.RecordListDto;
import com.lizekai.wms.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;
    //用于临时刷新数据库
//    @PostMapping("/test")
//    public void refreshVolume(){
//        recordService.refreshVolume();
//    }
    @GetMapping("/list")
    @SystemLog(businessName = "分页查询出入库列表")
    public ResponseResult getRecordList(RecordListDto dto, Integer pageNum, Integer pageSize){
        return recordService.getRecordList(dto,pageNum,pageSize);
    }
    @GetMapping("/listInApply")
    @SystemLog(businessName = "分页查询入库列表")
    public ResponseResult getInApplyList(RecordListDto dto, Integer pageNum, Integer pageSize){
        return recordService.getInApplyList(dto,pageNum,pageSize);
    }
    @GetMapping("/listOutApply")
    @SystemLog(businessName = "分页查询出库列表")
    public ResponseResult getOutApplyList(RecordListDto dto, Integer pageNum, Integer pageSize){
        return recordService.getOutApplyList(dto,pageNum,pageSize);
    }
    @GetMapping("/listAllotApply")
    @SystemLog(businessName = "分页查询调拨列表")
    public ResponseResult getAllotApplyList(RecordListDto dto, Integer pageNum, Integer pageSize){
        return recordService.getAllotApplyList(dto,pageNum,pageSize);
    }
    @GetMapping("/{id}")
    @SystemLog(businessName = "查询出入库信息")
    public ResponseResult getApplyById(@PathVariable("id") Long id){
        return recordService.getApplyById(id);
    }
    @PostMapping("addInApply")
    @SystemLog(businessName = "新增入库申请信息")
    public ResponseResult addInApply(@RequestBody AddApplyDto dto){
        return recordService.addInApply(dto);
    }
    @PostMapping("addOutApply")
    @SystemLog(businessName = "新增出库申请信息")
    public ResponseResult addOutApply(@RequestBody AddApplyDto dto){
        return recordService.addOutApply(dto);
    }
    @PostMapping("addAllotApply")
    @SystemLog(businessName = "新增调拨申请信息")
    public ResponseResult addAllotApply(@RequestBody AddApplyDto dto){
        return recordService.addAllotApply(dto);
    }
    @PostMapping("approvePass")
    @SystemLog(businessName = "出入库审批通过")
    public ResponseResult approvePass(@RequestBody ApproveDto dto){
        return recordService.approvePass(dto);
    }
    @PostMapping("approveReject")
    @SystemLog(businessName = "出入库审批驳回")
    public ResponseResult approveReject(@RequestBody ApproveDto dto){
        return recordService.approveReject(dto);
    }
    @PostMapping("preApprove")
    @SystemLog(businessName = "出入库预审批")
    public ResponseResult preApprove(){
        return recordService.preApprove();
    }
    @GetMapping("/export")
    @SystemLog(businessName = "导出出入库信息")
    //注意返回值类型是void
    public void export(HttpServletResponse response){
        recordService.export(response);
    }
}
