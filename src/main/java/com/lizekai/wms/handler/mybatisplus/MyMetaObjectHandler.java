package com.lizekai.wms.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.lizekai.wms.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MyBatis字段自动填充配置类
 * 通过实现MetaObjectHandler类并重写方法完成
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    //只要对数据库执行了插入语句，那么就会执行到这个方法
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
        try {
            //获取用户id
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            e.printStackTrace();
            userId = -1L;//如果异常了，就说明该用户还没注册，我们就把该用户的userid字段赋值d为-1
        }
        //自动把下面四个字段新增了值。
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy",userId , metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", SecurityUtils.getUserId(), metaObject);
    }
}
