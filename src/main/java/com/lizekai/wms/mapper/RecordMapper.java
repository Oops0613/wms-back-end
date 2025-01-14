package com.lizekai.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lizekai.wms.domain.entity.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    @Select(" SELECT r.role_key " +
            " FROM sys_user_role ur " +
            " LEFT JOIN sys_role r on ur.role_id=r.id " +
            " WHERE ur.user_id=#{userId} " +
            " AND r.`status`=0 " +
            " AND r.del_flag=0 ")
    IPage<String> selectRoleKeyByOtherUserId(Long userId);
}
