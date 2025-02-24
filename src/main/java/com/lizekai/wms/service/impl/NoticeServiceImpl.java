package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.mapper.NoticeMapper;
import com.lizekai.wms.domain.entity.Notice;
import com.lizekai.wms.service.NoticeService;
import org.springframework.stereotype.Service;

/**
 * 公告表(Notice)表服务实现类
 *
 * @author makejava
 * @since 2025-02-24 12:31:21
 */
@Service("noticeService")
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements NoticeService {

}

