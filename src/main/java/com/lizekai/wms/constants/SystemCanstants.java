package com.lizekai.wms.constants;

//字面值(代码中的固定值)处理，把字面值都在这里定义成常量
public class SystemCanstants {
    /**
     * 分类表的分类状态是正常状态
     */
    public static final int STATUS_NORMAL = 0;
    /**
     * 某个分类是一级分类
     */
    public static final String CATEGORY_ROOT = "-1";
    /**
     * 权限类型，顶级菜单（不可使用，仅展示）
     */
    public static final String TYPE_TOP_MENU = "M";
    /**
     * 权限类型，菜单
     */
    public static final String TYPE_MENU = "C";

    /**
     * 权限类型，按钮
     */
    public static final String TYPE_BUTTON = "F";
    /**
     * 判断为管理员用户
     */
    public static final String IS_ADMIN = "1";
    /**
     * 所有新增用户的初始密码
     */
    public static final String ORIGINAL_PASSWORD="1234";
    /**
     * 查询时忽略数量为0的库存
     */
    public static final String IGNORE_ZERO="1";
    /**
     * 入库申请类型
     */
    public static final String IN_APPLY="1";
    /**
     * 出库申请类型
     */
    public static final String OUT_APPLY="2";
    /**
     * 调拨申请类型
     */
    public static final String ALLOT_APPLY="3";
    /**
     * 货物有过期时间
     */
    public static final String CAN_EXPIRE="1";
    /**
     * 审批状态：未审批
     */
    public static final String NEED_APPROVE="0";
    /**
     * 审批状态：审批通过
     */
    public static final String APPROVE_PASS="1";
    /**
     * 审批状态：审批驳回
     */
    public static final String APPROVE_REJECT="2";
    /**
     * 审批状态：无法审批
     */
    public static final String APPROVE_UNAVAILABLE="3";
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
}
