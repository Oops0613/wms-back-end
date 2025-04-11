package com.lizekai.wms.controller;

import com.lizekai.wms.annotation.SystemLog;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.NoticeListDto;
import com.lizekai.wms.domain.entity.Notice;
import com.lizekai.wms.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;
    @GetMapping("/list")
    @SystemLog(businessName = "分页查询公告列表")
    public ResponseResult getNoticeList(NoticeListDto dto, Integer pageNum, Integer pageSize){
        return noticeService.getNoticeList(dto,pageNum,pageSize);
    }
    @PostMapping
    @SystemLog(businessName = "新增公告")
    public ResponseResult add( @RequestBody Notice notice) {
        return noticeService.insertNotice(notice);
    }
    @GetMapping(value = "/{noticeId}")
    @SystemLog(businessName = "查询公告信息")
    public ResponseResult getInfo(@PathVariable Long noticeId) {
        Notice notice = noticeService.getById(noticeId);
        return ResponseResult.okResult(notice);
    }
    @PutMapping
    @SystemLog(businessName = "修改公告")
    public ResponseResult edit(@RequestBody Notice notice) {
        return noticeService.updateNotice(notice);
    }
    @DeleteMapping("/{id}")
    @SystemLog(businessName = "删除公告")
    public ResponseResult remove(@PathVariable(name = "id") Long id) {
        return noticeService.removeNotice(id);
    }
    @GetMapping("/getLatestNotice")
    @SystemLog(businessName = "查询最新公告")
    public ResponseResult getLatestNotice(){
        return noticeService.getLatestNotice();
    }
    @GetMapping("/listPersonalNotice")
    @SystemLog(businessName = "分页查询个人公告列表")
    public ResponseResult listPersonalNotice(NoticeListDto dto, Integer pageNum, Integer pageSize){
        return noticeService.listPersonalNotice(dto,pageNum,pageSize);
    }
    @GetMapping("/getNoticeDetail/{noticeId}")
    @SystemLog(businessName = "查询公告详情")
    public ResponseResult getNoticeDetail(@PathVariable Long noticeId){
        return noticeService.getNoticeDetail(noticeId);
    }
    @GetMapping("/getUnreadAmount")
    @SystemLog(businessName = "查询未读公告数")
    public ResponseResult getUnreadAmount(){
        return noticeService.getUnreadAmount();
    }
}
