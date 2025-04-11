package com.lizekai.wms.controller;

import com.lizekai.wms.annotation.SystemLog;
import com.lizekai.wms.constants.SystemConstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.Role;
import com.lizekai.wms.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @GetMapping("/list")
    @SystemLog(businessName = "分页查询角色列表")
    public ResponseResult list(Role role, Integer pageNum, Integer pageSize) {
        return roleService.selectRolePage(role,pageNum,pageSize);
    }
    @PostMapping
    @SystemLog(businessName = "新增角色")
    public ResponseResult add( @RequestBody Role role) {
        return roleService.insertRole(role);
    }
    //----------------------修改角色-根据角色id查询对应的角色-----------------------------

    @GetMapping(value = "/{roleId}")
    @SystemLog(businessName = "查询角色信息")
    public ResponseResult getInfo(@PathVariable Long roleId) {
        Role role = roleService.getById(roleId);
        return ResponseResult.okResult(role);
    }
    @PutMapping
    @SystemLog(businessName = "修改角色")
    public ResponseResult edit(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @DeleteMapping("/{id}")
    @SystemLog(businessName = "删除角色")
    public ResponseResult remove(@PathVariable(name = "id") Long id) {
        //不能删除超级管理员
        if(SystemConstants.IS_ADMIN.equals(id.toString())){
            return ResponseResult.errorResult(500,"不能删除超级管理员");
        }
        roleService.removeById(id);
        return ResponseResult.okResult();
    }

    @GetMapping("/listAllRole")
    @SystemLog(businessName = "查询所有角色列表")
    public ResponseResult listAllRole(){
        return ResponseResult.okResult(roleService.selectRoleAll());
    }
}
