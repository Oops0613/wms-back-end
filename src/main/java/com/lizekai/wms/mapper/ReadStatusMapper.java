package com.lizekai.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizekai.wms.domain.entity.ReadStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ReadStatusMapper extends BaseMapper<ReadStatus> {
    @Update(" UPDATE wms_read_status " +
            " SET is_read=1 " +
            " WHERE notice_id=#{noticeId} AND user_id=#{userId} ")
    void updateReadStatus(@Param("noticeId") Long noticeId,@Param("userId") Long userId);
}
