package com.lizekai.wms.constants;

//字面值(代码中的固定值)处理，把字面值都在这里定义成常量
public class SystemConstants {
    /**
     * 正常状态
     */
    public static final int STATUS_NORMAL = 0;
    /**
     * 某个分类是一级分类
     */
    public static final String CATEGORY_ROOT = "-1";
    /**
     * 所有新增用户的初始密码
     */
    public static final String ORIGINAL_PASSWORD="1234";
    /**
     * 查询时忽略数量为0的库存
     */
    public static final String IGNORE_ZERO="1";
    /**
     * 货物有过期时间
     */
    public static final String CAN_EXPIRE="1";
    /**
     * 仓库负载纵坐标单位：使用率
     */
    public static final String LOAD_RATE="0";
    /**
     * 过期预警的阈值（剩几天过期）
     */
    public static final int DAYS_TO_EXPIRE=3;
    /**
     * 展示图表时精确到二级分类
     */
    public static final String IS_PRECISE="1";
    /**
     * 表示未读状态
     */
    public static final String IS_UNREAD="0";
    /**
     * 表示已读状态
     */
    public static final String IS_READ="1";
    /**
     * 库存监控-启用状态
     */
    public static final String TASK_ON="1";
    /**
     * 库存监控-禁用状态
     */
    public static final String TASK_OFF="0";

}
