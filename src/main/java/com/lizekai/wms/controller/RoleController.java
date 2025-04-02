package com.lizekai.wms.controller;

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

    //------------------------------查询角色列表---------------------------------------
    @GetMapping("/list")
    public ResponseResult list(Role role, Integer pageNum, Integer pageSize) {
        return roleService.selectRolePage(role,pageNum,pageSize);
    }
    //-------------------------------新增角色-----------------------------------------

    @PostMapping
    public ResponseResult add( @RequestBody Role role) {
        return roleService.insertRole(role);
    }
    //----------------------修改角色-根据角色id查询对应的角色-----------------------------

    @GetMapping(value = "/{roleId}")
    public ResponseResult getInfo(@PathVariable Long roleId) {
        Role role = roleService.getById(roleId);
        return ResponseResult.okResult(role);
    }
    //-------------------------修改角色-保存修改好的角色信息------------------------------

    @PutMapping
    public ResponseResult edit(@RequestBody Role role) {
        return roleService.updateRole(role);
    }
    //--------------------------------删除角色---------------------------------------

    @DeleteMapping("/{id}")
    public ResponseResult remove(@PathVariable(name = "id") Long id) {
        //不能删除超级管理员
        if(SystemConstants.IS_ADMIN.equals(id.toString())){
            return ResponseResult.errorResult(500,"不能删除超级管理员");
        }
        roleService.removeById(id);
        return ResponseResult.okResult();
    }

    //--------------------------------新增用户---------------------------------------

    @GetMapping("/listAllRole")
    //①查询角色列表接口
    public ResponseResult listAllRole(){
        return ResponseResult.okResult(roleService.selectRoleAll());
    }
}
