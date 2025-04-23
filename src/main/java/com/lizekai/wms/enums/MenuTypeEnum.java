package com.lizekai.wms.enums;

/**
 * 菜单类型枚举类
 */
public enum MenuTypeEnum {
    TYPE_TOP_MENU("M","顶级菜单（不可使用，仅展示）"),
    TYPE_MENU("C","菜单"),
    TYPE_BUTTON("F","按钮");

    final String code;
    final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    MenuTypeEnum(String code, String msg) {
        this.code=code;
        this.msg=msg;
    }
}
