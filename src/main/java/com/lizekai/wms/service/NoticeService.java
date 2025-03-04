package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.NoticeListDto;
import com.lizekai.wms.domain.entity.Notice;
import org.springframework.stereotype.Service;

/**
 * 公告表(Notice)表服务接口
 *
 * @author makejava
 * @since 2025-02-24 12:31:21
 */
@Service
public interface NoticeService extends IService<Notice> {

    ResponseResult getNoticeList(NoticeListDto dto, Integer pageNum, Integer pageSize);

    ResponseResult insertNotice(Notice notice);

    ResponseResult updateNotice(Notice notice);

    ResponseResult removeNotice(Long id);

    ResponseResult getLatestNotice();
}

