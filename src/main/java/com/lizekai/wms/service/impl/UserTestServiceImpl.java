package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.vo.PageVo;
import com.lizekai.wms.mapper.UserTestMapper;
import com.lizekai.wms.domain.entity.UserTest;
import com.lizekai.wms.service.UserTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (UserTest)表服务实现类
 *
 * @author makejava
 * @since 2024-11-23 10:54:22
 */
@Service("userTestService")
public class UserTestServiceImpl extends ServiceImpl<UserTestMapper, UserTest> implements UserTestService {
    @Autowired
    private UserTestMapper userTestMapper;

    @Override
    public ResponseResult getAllUser(Long pageNum, Long pageSize) {
        Page<UserTest> page=new Page<>(pageNum,pageSize);
        page(page);
        PageVo pageVo=new PageVo(page.getRecords(),page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult addUser(UserTest user) {
        if(StringUtils.isNotBlank(user.getName())){
            UserTest insertUser=new UserTest();
            insertUser.setName(user.getName());
            save(insertUser);
            return ResponseResult.okResult();
        }
        return ResponseResult.errorResult(400,"姓名不能为空");
    }
}

