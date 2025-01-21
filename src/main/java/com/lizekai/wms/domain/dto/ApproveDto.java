package com.lizekai.wms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApproveDto {
    //申请ID
    private Long id;
    //申请类型（1入库2出库3调拨）
    private String type;
    //审批备注
    private String approveRemark;
}
