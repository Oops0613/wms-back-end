package com.lizekai.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizekai.wms.domain.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    @Select(" SELECT * FROM " +
            " wms_notice AS n INNER JOIN wms_notice_role AS nr " +
            " ON n.id = nr.notice_id " +
            " WHERE role_id=#{roleId} " +
            " ORDER BY create_time DESC " +
            " LIMIT 1 ")
    List<Notice> getLatestNotice(Long roleId);
}
