package com.lizekai.wms.controller;

import com.lizekai.wms.annotation.SystemLog;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.GetWorkLoadDto;
import com.lizekai.wms.domain.entity.LoginUser;
import com.lizekai.wms.domain.entity.Menu;
import com.lizekai.wms.domain.entity.User;
import com.lizekai.wms.domain.vo.RoutersVo;
import com.lizekai.wms.enums.AppHttpCodeEnum;
import com.lizekai.wms.handler.exception.SystemException;
import com.lizekai.wms.service.MenuService;
import com.lizekai.wms.service.SystemLoginService;
import com.lizekai.wms.service.UserService;
import com.lizekai.wms.service.UserStatService;
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
    @Autowired
    private UserStatService userStatService;

    @GetMapping("/list")
    @SystemLog(businessName = "分页查询用户列表")
    public ResponseResult list(User user, Integer pageNum, Integer pageSize) {
        return userService.selectUserList(user,pageNum,pageSize);
    }
    @PostMapping("/add")
    @SystemLog(businessName = "新增用户")
    public ResponseResult add(@RequestBody User user) {
        return userService.addUser(user);
    }
    @GetMapping(value = { "/{userId}" })
    @SystemLog(businessName = "查询用户信息")
    public ResponseResult getUserInfo(@PathVariable(value = "userId") Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/edit")
    @SystemLog(businessName = "编辑用户")
    public ResponseResult edit(@RequestBody User user) {
        return userService.editUser(user);
    }
    @PostMapping("/resetPassword/{userId}")
    @SystemLog(businessName = "重置用户密码")
    public ResponseResult resetPassword(@PathVariable Long userId){
        return userService.resetPassword(userId);
    }
    @PutMapping("/editPassword")
    @SystemLog(businessName = "用户修改密码")
    public ResponseResult editPassword(@RequestBody User user) {
        return userService.editPassword(user);
    }
    @DeleteMapping("/delete/{userId}")
    @SystemLog(businessName = "删除用户")
    public ResponseResult remove(@PathVariable Long userId) {
        if(userId.equals(SecurityUtils.getUserId())){
            return ResponseResult.errorResult(500,"不能删除当前你正在使用的用户");
        }
        userService.removeById(userId);
        return ResponseResult.okResult();
    }
    @PostMapping("/login")
    @SystemLog(businessName = "用户登录")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return systemLoginService.login(user);
    }
    @GetMapping("/getInfo")
    @SystemLog(businessName = "获取当前登录用户信息")
    public ResponseResult getInfo(){
        LoginUser loginUser = SecurityUtils.getLoginUser();
        // 用户信息包括权限信息
        Long userId=loginUser.getUser().getId();
        List<String> perms = menuService.selectPermsByUserId(userId);
        loginUser.setPermissions(perms);
        //List<String> roleKeyList=roleService.selectRoleKeyByUserId(userId);
        //UserInfoVo userInfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        //AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms, roleKeyList, userInfoVo);
        return ResponseResult.okResult(loginUser);
    }
    @PostMapping("/logout")
    @SystemLog(businessName = "用户登出")
    public ResponseResult logout(){
        return systemLoginService.logout();
    }
    @GetMapping("/getRouters")
    @SystemLog(businessName = "查询当前登录用户路由")
    public ResponseResult<RoutersVo> getRouters(){
        Long userId=SecurityUtils.getUserId();

        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);

        return ResponseResult.okResult(new RoutersVo(menus));
    }
    @GetMapping("/getWorkLoad")
    @SystemLog(businessName = "查询工作量统计数据")
    public ResponseResult getWorkLoad(GetWorkLoadDto dto){
        return userStatService.getWorkLoad(dto);
    }
}
