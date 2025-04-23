package com.lizekai.wms.enums;

/**
 * 出入库审批状态枚举类
 */
public enum ApproveStatusEnum {
    NEED_APPROVE("0","未审批"),
    APPROVE_PASS("1","审批通过"),
    APPROVE_REJECT("2","审批驳回"),
    APPROVE_UNAVAILABLE("3","无法审批");
    final String code;
    final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ApproveStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static ApproveStatusEnum getApproveStatusByCode(String code){
        for (ApproveStatusEnum e:ApproveStatusEnum.values()){
            if(e.getCode().equals(code)){
                return e;
            }
        }
        return null;
    }
}
