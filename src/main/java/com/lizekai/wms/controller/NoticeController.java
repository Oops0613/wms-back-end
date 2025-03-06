package com.lizekai.wms.controller;

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
    public ResponseResult getNoticeList(NoticeListDto dto, Integer pageNum, Integer pageSize){
        return noticeService.getNoticeList(dto,pageNum,pageSize);
    }
    @PostMapping
    public ResponseResult add( @RequestBody Notice notice) {
        return noticeService.insertNotice(notice);
    }
    @GetMapping(value = "/{noticeId}")
    public ResponseResult getInfo(@PathVariable Long noticeId) {
        Notice notice = noticeService.getById(noticeId);
        return ResponseResult.okResult(notice);
    }
    @PutMapping
    public ResponseResult edit(@RequestBody Notice notice) {
        return noticeService.updateNotice(notice);
    }
    @DeleteMapping("/{id}")
    public ResponseResult remove(@PathVariable(name = "id") Long id) {
        return noticeService.removeNotice(id);
    }
    @GetMapping("/getLatestNotice")
    public ResponseResult getLatestNotice(){
        return noticeService.getLatestNotice();
    }
    @GetMapping("/listPersonalNotice")
    public ResponseResult listPersonalNotice(NoticeListDto dto, Integer pageNum, Integer pageSize){
        return noticeService.listPersonalNotice(dto,pageNum,pageSize);
    }
    @GetMapping("/getNoticeDetail/{noticeId}")
    public ResponseResult getNoticeDetail(@PathVariable Long noticeId){
        return noticeService.getNoticeDetail(noticeId);
    }
    @GetMapping("/getUnreadAmount")
    public ResponseResult getUnreadAmount(){
        return noticeService.getUnreadAmount();
    }
}
