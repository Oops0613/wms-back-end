package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2024-12-10 15:31:55
 */
@Service
public interface RoleService extends IService<Role> {
    //查询用户的角色信息
    List<String> selectRoleKeyByUserId(Long id);
    //查询角色列表
    ResponseResult selectRolePage(Role role, Integer pageNum, Integer pageSize);
    //新增角色
    ResponseResult insertRole(Role role);
    //修改角色-保存修改好的角色信息
    ResponseResult updateRole(Role role);
    //删除角色
    ResponseResult removeRole(Long roleId);
    //新增用户-①查询角色列表接口
    List<Role> selectRoleAll();

    Role getRoleByUserId(Long userId);
}

