package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemCanstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.Role;
import com.lizekai.wms.domain.vo.PageVo;
import com.lizekai.wms.enums.AppHttpCodeEnum;
import com.lizekai.wms.handler.exception.SystemException;
import com.lizekai.wms.mapper.UserMapper;
import com.lizekai.wms.domain.entity.User;
import com.lizekai.wms.service.RoleService;
import com.lizekai.wms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 用户表(User)表服务实现类
 *
 * @author makejava
 * @since 2024-12-01 11:44:52
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleService roleService;
    @Override
    public ResponseResult selectUserList(User user, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.like(StringUtils.hasText(user.getUserName()),User::getUserName, user.getUserName());
        queryWrapper.like(StringUtils.hasText(user.getRealName()),User::getRealName, user.getRealName());
        queryWrapper.eq(StringUtils.hasText(user.getPhonenumber()),User::getPhonenumber, user.getPhonenumber());

        Page<User> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);

        //转换成VO
        //List<UserVo> userVos = BeanCopyUtils.copyBeanList(page.getRecords(), UserVo.class);
        return ResponseResult.okResult(new PageVo(page.getRecords(),page.getTotal()));
    }

    @Override
    public ResponseResult addUser(User user) {
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getRealName())){
            throw new SystemException(AppHttpCodeEnum.REALNAME_NOT_NULL);
        }
        //密码新增时自动填充初始密码
//        if(!StringUtils.hasText(user.getPassword())){
//            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
//        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getRoleId().toString())){
            throw new SystemException(AppHttpCodeEnum.ROLE_NOT_NULL);
        }
        // 用户名是否重复
        if(existUserName(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_EXIST);
        }

        user.setPassword(passwordEncoder.encode(SystemCanstants.ORIGINAL_PASSWORD));
        save(user);
//TODO@用户角色
//        if(!ArrayUtils.isEmpty(user.getRoleIds())){
//            insertUserRole(user);
//        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult editUser(User user) {
        if(!StringUtils.hasText(user.getUserName())){
            throw new SystemException(AppHttpCodeEnum.USERNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getRealName())){
            throw new SystemException(AppHttpCodeEnum.REALNAME_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getEmail())){
            throw new SystemException(AppHttpCodeEnum.EMAIL_NOT_NULL);
        }
        if(!StringUtils.hasText(user.getRoleId().toString())){
            throw new SystemException(AppHttpCodeEnum.ROLE_NOT_NULL);
        }
        // 更新用户信息
        updateById(user);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getUserById(Long userId) {
        User user=getById(userId);
        return ResponseResult.okResult(user);
    }

    @Override
    public ResponseResult editPassword(User user) {
        if(!StringUtils.hasText(user.getPassword())){
            throw new SystemException(AppHttpCodeEnum.PASSWORD_NOT_NULL);
        }
        Long id=user.getId();
        String encodedPwd=passwordEncoder.encode(user.getPassword());
        userMapper.updatePasswordById(id,encodedPwd);
        return ResponseResult.okResult(user);
    }

    @Override
    public ResponseResult resetPassword(Long userId) {
        User user=getById(userId);
        user.setPassword(passwordEncoder.encode(SystemCanstants.ORIGINAL_PASSWORD));
        updateById(user);
        return ResponseResult.okResult();
    }

    private boolean existUserName(String userName){
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName,userName);
        return count(wrapper)>0;
    }
}

