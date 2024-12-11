package com.lizekai.wms.controller;

import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.LoginUser;
import com.lizekai.wms.domain.entity.Menu;
import com.lizekai.wms.domain.entity.User;
import com.lizekai.wms.domain.vo.RoutersVo;
import com.lizekai.wms.enums.AppHttpCodeEnum;
import com.lizekai.wms.handler.exception.SystemException;
import com.lizekai.wms.service.MenuService;
import com.lizekai.wms.service.SystemLoginService;
import com.lizekai.wms.service.UserService;
import com.lizekai.wms.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private UserService userService;
    @Autowired
    private SystemLoginService systemLoginService;

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
    public ResponseResult getUserInfo(@PathVariable(value = "userId") Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/edit")
    public ResponseResult edit(@RequestBody User user) {
        return userService.editUser(user);
    }
    @PostMapping("/resetPassword/{userId}")
    public ResponseResult resetPassword(@PathVariable Long userId){
        return userService.resetPassword(userId);
    }
    @PutMapping("/editPassword")
    public ResponseResult editPassword(@RequestBody User user) {
        return userService.editPassword(user);
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseResult remove(@PathVariable Long userId) {
        if(userId.equals(SecurityUtils.getUserId())){
            return ResponseResult.errorResult(500,"不能删除当前你正在使用的用户");
        }
        userService.removeById(userId);
        return ResponseResult.okResult();
    }


    @PostMapping("/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return systemLoginService.login(user);
    }
    @GetMapping("/getInfo")
    public ResponseResult getInfo(){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //TODO@用户信息包括权限信息
        //Long userId=loginUser.getUser().getId();

        //List<String> perms = menuService.selectPermsByUserId(userId);

        //List<String> roleKeyList=roleService.selectRoleKeyByUserId(userId);

        //UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);

        //AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);

        return ResponseResult.okResult(loginUser);
    }
    @PostMapping("/logout")
    public ResponseResult logout(){
        return systemLoginService.logout();
    }
    @GetMapping("/getRouters")
    public ResponseResult<RoutersVo> getRouters(){
        Long userId=SecurityUtils.getUserId();

        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);

        return ResponseResult.okResult(new RoutersVo(menus));
    }
}
