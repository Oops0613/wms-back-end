package com.lizekai.wms.enums;

/**
 * 自定义消息体枚举类
 * 与ResponseResult配合使用
 */
public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    ROLENAME_EXIST(502,"角色名已存在"),
    WAREHOUSE_NAME_EXIST(503, "仓库名已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    GOODS_NAME_EXIST(506, "货物名已存在"),
    CATEGORY_NAME_EXIST(507, "分类名已存在"),
    USERNAME_NOT_NULL(508, "用户名不能为空"),
    REALNAME_NOT_NULL(509, "姓名不能为空"),
    PASSWORD_NOT_NULL(510, "密码不能为空"),
    EMAIL_NOT_NULL(511, "邮箱不能为空"),
    NICKNAME_EXIST(512, "昵称已经存在"),
    PHONENUMBER_NOT_NULL(521, "手机号码不能为空"),
    ROLE_NOT_NULL(522,"角色不能为空"),
    OUT_OF_CAPACITY(530,"目的仓库剩余容量不足，无法入库"),
    GOODS_EXPIRED(531,"货物过期，无法入库"),
    INVENTORY_INSUFFICIENT(532,"该批次库存数量不足，无法出库"),
    FILE_SIZE_ERROR(413, "文件大小不能超出2MB");
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
