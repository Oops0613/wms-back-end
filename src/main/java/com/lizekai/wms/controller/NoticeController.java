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
}
