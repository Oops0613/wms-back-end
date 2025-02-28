package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.mapper.NoticeRoleMapper;
import com.lizekai.wms.domain.entity.NoticeRole;
import com.lizekai.wms.service.NoticeRoleService;
import org.springframework.stereotype.Service;

/**
 * 公告和收件角色关联表(NoticeRole)表服务实现类
 *
 * @author makejava
 * @since 2025-02-24 12:32:58
 */
@Service("noticeRoleService")
public class NoticeRoleServiceImpl extends ServiceImpl<NoticeRoleMapper, NoticeRole> implements NoticeRoleService {

}

