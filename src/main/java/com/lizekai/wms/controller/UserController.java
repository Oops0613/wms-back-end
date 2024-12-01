package com.lizekai.wms.controller;

import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.User;
import com.lizekai.wms.service.UserService;
import com.lizekai.wms.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //--------------------------------查询用户列表-------------------------------------

    @GetMapping("/list")
    public ResponseResult list(User user, Integer pageNum, Integer pageSize) {
        return userService.selectUserList(user,pageNum,pageSize);
    }
    @PostMapping("/add")
    public ResponseResult add(@RequestBody User user) {
        return userService.addUser(user);
    }
    @GetMapping(value = { "/{userId}" })
    public ResponseResult getUserInfoAndRoleIds(@PathVariable(value = "userId") Long userId) {
        return userService.getUserById(userId);
    }
    @PutMapping("/edit")
    public ResponseResult edit(@RequestBody User user) {
        return userService.editUser(user);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseResult remove(@PathVariable Long userId) {
        if(userId.equals(SecurityUtils.getUserId())){
            return ResponseResult.errorResult(500,"不能删除当前你正在使用的用户");
        }
        userService.removeById(userId);
        return ResponseResult.okResult();
    }
}
