package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.UserTest;
import org.springframework.stereotype.Service;

/**
 * (UserTest)表服务接口
 *
 * @author makejava
 * @since 2024-11-23 10:54:19
 */
@Service
public interface UserTestService extends IService<UserTest> {
    public ResponseResult getAllUser(Long pageNum,Long pageSize);

    ResponseResult addUser(UserTest user);
}

