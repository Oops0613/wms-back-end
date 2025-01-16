package com.lizekai.wms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddApplyDto {
    //货物id
    private Long goodsId;
    //源仓库id,入库记录为-1
    private Long fromId;
    //目的仓库id,出库记录为-1
    private Long toId;
    //申请数量
    private Double amount;
    //货物过期时间
    private Date expirationTime;
    //申请备注
    private String applyRemark;
}
