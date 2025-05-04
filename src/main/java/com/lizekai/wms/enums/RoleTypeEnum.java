package com.lizekai.wms.enums;

public enum RoleTypeEnum {
    ROLE_SUPER_ADMIN(1L,"超级管理员"),
    ROLE_INVENTORY(4L,"库存管理员"),
    ROLE_APPROVE(5L,"审批员");
    final Long code;
    final String msg;

    public Long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    RoleTypeEnum(Long code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
