package com.lizekai.wms.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplyDetailVo {
    //申请ID
    private Long id;
    //所属库存id
    private Long inventoryId;
    //源仓库名
    private String fromName;
    //目的仓库名
    private String toName;
    //分类名
    private String categoryName;
    //货物名
    private String goodsName;
    //移动数量
    private Double amount;
    //占据空间
    private Double volume;
    //发起人姓名
    private String applyUserName;
    //发起时间
    private Date applyTime;
    //申请备注
    private String applyRemark;
    //审批人姓名
    private String approveUserName;
    //审批时间
    private Date approveTime;
    //审批状态0:未审批,1审批通过,2审批驳回,3无法审批
    private String approveStatus;
    //审批备注
    private String approveRemark;
    //过期适用标志位0:无,1有
    private String hasExpirationTime;
    //过期时间
    private Date expirationTime;
}
