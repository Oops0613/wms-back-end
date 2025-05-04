package com.lizekai.wms.enums;

/**
 * 出入库申请类型枚举类
 */
public enum ApplyTypeEnum {
    IN_APPLY("1","入库申请"),
    OUT_APPLY("2","出库申请"),
    ALLOT_APPLY("3","调拨申请");
    final String code;
    final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ApplyTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static ApplyTypeEnum getApplyTypeByCode(String code){
        for (ApplyTypeEnum e:ApplyTypeEnum.values()){
            if(e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }
}
