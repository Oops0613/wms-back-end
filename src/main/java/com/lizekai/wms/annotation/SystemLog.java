package com.lizekai.wms.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//表示SystemLog注解类会保持到runtime阶段
@Target(ElementType.METHOD) //表示SystemLog注解类的注解功能只能用于方法上
public @interface SystemLog {
    //为controller提供接口的描述信息，用于'日志记录'功能
    String businessName();
}
