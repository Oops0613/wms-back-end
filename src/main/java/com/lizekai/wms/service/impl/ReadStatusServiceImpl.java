package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.mapper.ReadStatusMapper;
import com.lizekai.wms.domain.entity.ReadStatus;
import com.lizekai.wms.service.ReadStatusService;
import org.springframework.stereotype.Service;

/**
 * 公告已读状态表(ReadStatus)表服务实现类
 *
 * @author makejava
 * @since 2025-02-24 13:01:49
 */
@Service("readStatusService")
public class ReadStatusServiceImpl extends ServiceImpl<ReadStatusMapper, ReadStatus> implements ReadStatusService {

}

