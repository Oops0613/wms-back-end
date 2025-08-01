package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lizekai.wms.domain.entity.LoginUser;
import com.lizekai.wms.domain.entity.User;
import com.lizekai.wms.enums.AppHttpCodeEnum;
import com.lizekai.wms.enums.RoleTypeEnum;
import com.lizekai.wms.handler.exception.SystemException;
import com.lizekai.wms.mapper.MenuMapper;
import com.lizekai.wms.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,username);
        User user=userMapper.selectOne(wrapper);

        if(Objects.isNull(user)){
            throw new SystemException(AppHttpCodeEnum.LOGIN_ERROR);
        }
        // 查询权限信息封装
        if(!RoleTypeEnum.ROLE_SUPER_ADMIN.getCode().equals(user.getRoleId())){
            List<String> perms = menuMapper.selectPermsByOtherUserId(user.getId());
            return new LoginUser(user,perms);
        }
        //如果是超级管理员直接返回
        return new LoginUser(user,null);
    }
}
