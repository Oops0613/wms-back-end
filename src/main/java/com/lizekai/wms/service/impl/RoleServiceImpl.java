package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemConstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.RoleMenu;
import com.lizekai.wms.domain.vo.PageVo;
import com.lizekai.wms.enums.AppHttpCodeEnum;
import com.lizekai.wms.enums.RoleTypeEnum;
import com.lizekai.wms.handler.exception.SystemException;
import com.lizekai.wms.mapper.RoleMapper;
import com.lizekai.wms.domain.entity.Role;
import com.lizekai.wms.service.RoleMenuService;
import com.lizekai.wms.service.RoleService;
import com.lizekai.wms.service.UserService;
import com.lizekai.wms.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2024-12-10 15:31:55
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private UserService userService;
    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //用户是管理员
        if(SecurityUtils.isAdmin()){
            List<String> roleKeys = new ArrayList<>();
            roleKeys.add("admin");
            return roleKeys;
        }
        return roleMapper.selectRoleKeyByOtherUserId(id);
    }

    @Override
    public ResponseResult selectRolePage(Role role, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Role> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.hasText(role.getRoleName()),Role::getRoleName,role.getRoleName());
        lambdaQueryWrapper.eq(StringUtils.hasText(role.getStatus()),Role::getStatus,role.getStatus());
        //lambdaQueryWrapper.orderByAsc(Role::getRoleSort);

        Page<Role> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,lambdaQueryWrapper);

        //转换成VO
        List<Role> roles = page.getRecords();

        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal());
        pageVo.setRows(roles);
        return ResponseResult.okResult(pageVo);
    }



    @Override
    @Transactional
    public ResponseResult insertRole(Role role) {
        if(existRoleName(role.getRoleName())){
            throw new SystemException(AppHttpCodeEnum.ROLENAME_EXIST);
        }
        save(role);
        if(CollectionUtils.isEmpty(role.getMenuIds())){
            insertRoleMenu(role);
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateRole(Role role) {
        //不能修改超级管理员
        if(RoleTypeEnum.ROLE_SUPER_ADMIN.getCode().equals(role.getId())){
            return ResponseResult.errorResult(500,"不能修改超级管理员");
        }
        Role oldRole=getById(role.getId());
        //如果角色名有修改，检查唯一性
        if(!oldRole.getRoleName().equals(role.getRoleName())){
            if(existRoleName(role.getRoleName())){
                throw new SystemException(AppHttpCodeEnum.ROLENAME_EXIST);
            }
        }
        updateById(role);
        roleMenuService.deleteRoleMenuByRoleId(role.getId());
        insertRoleMenu(role);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult removeRole(Long roleId) {
        //不能删除超级管理员
        if(RoleTypeEnum.ROLE_SUPER_ADMIN.getCode().equals(roleId)){
            return ResponseResult.errorResult(500,"不能删除超级管理员");
        }
        //不能删除正在使用的角色
        if(userService.countUserByRole(roleId)>0){
            return ResponseResult.errorResult(500,"不能删除正在使用的角色");
        }
        removeById(roleId);
        return ResponseResult.okResult();
    }

    @Override
    public List<Role> selectRoleAll() {
        LambdaQueryWrapper<Role> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Role::getStatus, SystemConstants.STATUS_NORMAL);
        return list(wrapper);
    }

    @Override
    public Role getRoleByUserId(Long userId) {
        Role role =roleMapper.selectRoleByUserId(userId);
        return role;
    }

    private void insertRoleMenu(Role role) {
        List<RoleMenu> roleMenuList = role.getMenuIds().stream()
                .map(menuId -> new RoleMenu(role.getId(), menuId))
                .collect(Collectors.toList());
        roleMenuService.saveBatch(roleMenuList);
    }
    private boolean existRoleName(String roleName){
        LambdaQueryWrapper<Role> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Role::getRoleName,roleName);
        return count(wrapper)>0;
    }
}

