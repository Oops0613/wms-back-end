package com.lizekai.wms.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 出入库记录表(Record)表实体类
 *
 * @author makejava
 * @since 2025-01-13 10:31:38
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="wms_record")
public class Record {

    private Long id;
    //申请类型（1入库2出库3调拨）
    private String type;
    //源仓库id,入库记录为-1
    private Long fromId;
    //源仓库名
    private String fromName;
    //目的仓库id,出库记录为-1
    private Long toId;
    //目的仓库名
    private String toName;
    //所属库存id
    private Long inventoryId;
    //调拨后新产生的库存id
    private Long newInventoryId;
    //分类id
    private Long categoryId;
    //分类名
    private String categoryName;
    //货物id
    private Long goodsId;
    //货物名
    private String goodsName;
    //移动数量
    private Double amount;
    //发起人ID
    private Long applyBy;
    //发起时间
    private Date applyTime;
    //申请备注
    private String applyRemark;
    //审批人ID
    private Long approveBy;
    //审批时间
    private Date approveTime;
    //审批状态0:未审批,1审批通过,2审批驳回,3无法审批
    private String approveStatus;
    //审批备注
    private String approveRemark;
    //状态0:正常,1禁用
    private String status;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;
    //过期适用标志位0:无,1有
    private String hasExpirationTime;
    //过期时间
    private Date expirationTime;

}

