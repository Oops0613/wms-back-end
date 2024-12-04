package com.lizekai.wms.service;

import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.User;

public interface SystemLoginService {

    //登录
    ResponseResult login(User user);

    //退出登录
    ResponseResult logout();
}
