package com.lizekai.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizekai.wms.domain.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    //查询普通用户的角色权限
    @Select(" SELECT r.role_key " +
            " FROM sys_user_role ur " +
            " LEFT JOIN sys_role r on ur.role_id=r.id " +
            " WHERE ur.user_id=#{userId} " +
            " AND r.`status`=0 " +
            " AND r.del_flag=0 ")
    List<String> selectRoleKeyByOtherUserId(Long userId);
}
