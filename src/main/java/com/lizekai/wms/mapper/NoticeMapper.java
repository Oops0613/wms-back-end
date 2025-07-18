package com.lizekai.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lizekai.wms.domain.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    @Deprecated
    @Select("<script>" +
            " SELECT * FROM " +
            " wms_notice AS n INNER JOIN wms_notice_role AS nr " +
            " ON n.id = nr.notice_id " +
            "<where>" +
            " role_id=#{roleId} " +
            "   <if test='keyWord!=null'>" +
            "       AND ( title LIKE CONCAT('%', #{keyWord}, '%') " +
            "       OR content LIKE CONCAT('%', #{keyWord}, '%') ) " +
            "   </if>" +
            "</where>" +
            " ORDER BY create_time DESC " +
            "</script>")
    Page<Notice> listPersonalNotice(Page<Notice> page, @Param("roleId")Long roleId,@Param("keyWord") String keyWord);

    @Select("<script>" +
            " SELECT * FROM " +
            " wms_notice AS n INNER JOIN wms_read_status AS rs " +
            " ON n.id=rs.notice_id " +
            "<where>" +
            " user_id=#{userId} " +
            "   <if test='isRead!=null'>" +
            "       AND is_read=#{isRead} " +
            "   </if>" +
            "   <if test='keyWord!=null'>" +
            "       AND ( title LIKE CONCAT('%', #{keyWord}, '%') " +
            "       OR content LIKE CONCAT('%', #{keyWord}, '%') ) " +
            "   </if>" +
            "</where>" +
            " ORDER BY create_time DESC " +
            "</script>")
    Page<Notice> listPersonalNoticeByStatus(Page<Notice> page, @Param("userId")Long userId,@Param("keyWord") String keyWord,@Param("isRead") String isRead);
}
