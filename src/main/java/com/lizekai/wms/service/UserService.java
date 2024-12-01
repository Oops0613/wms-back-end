package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.User;
import org.springframework.stereotype.Service;

/**
 * 用户表(User)表服务接口
 *
 * @author makejava
 * @since 2024-12-01 11:44:52
 */
@Service
public interface UserService extends IService<User> {

    ResponseResult selectUserList(User user, Integer pageNum, Integer pageSize);

    ResponseResult addUser(User user);

    ResponseResult editUser(User user);

    ResponseResult getUserById(Long userId);
}

