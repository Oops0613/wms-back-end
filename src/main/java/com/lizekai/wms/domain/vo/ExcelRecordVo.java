package com.lizekai.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelRecordVo {
    @ExcelProperty("出入库ID")
    private Long id;
    @ExcelProperty("申请类型")
    private String typeName;
    @ExcelIgnore
    //申请类型（1入库2出库3调拨）
    private String type;
    @ExcelProperty("源仓库ID")
    private Long fromId;
    @ExcelProperty("源仓库名")
    @ColumnWidth(20)
    private String fromName;
    @ExcelProperty("目的仓库ID")
    private Long toId;
    @ExcelProperty("目的仓库名")
    @ColumnWidth(20)
    private String toName;
    @ExcelProperty("库存ID")
    private Long inventoryId;
    @ExcelProperty("新库存ID")
    private Long newInventoryId;
    @ExcelProperty("分类ID")
    private Long categoryId;
    @ExcelProperty("分类名")
    @ColumnWidth(20)
    private String categoryName;
    @ExcelProperty("货物ID")
    private Long goodsId;
    @ExcelProperty("货物名")
    @ColumnWidth(20)
    private String goodsName;
    @ExcelProperty("申请数量")
    private Double amount;
    @ExcelProperty("所占容积（升）")
    private Double volume;
    @ExcelProperty("过期时间")
    @ColumnWidth(20)
    private Date expirationTime;
    @ExcelProperty("申请人")
    private String applyByName;
    @ExcelProperty("申请时间")
    @ColumnWidth(20)
    private Date applyTime;
    @ExcelProperty("申请备注")
    private String applyRemark;
    @ExcelProperty("审批人")
    private String approveByName;
    @ExcelProperty("审批时间")
    @ColumnWidth(20)
    private Date approveTime;
    @ExcelProperty("审批备注")
    private String approveRemark;
    @ExcelProperty("审批状态")
    private String approveStatusName;

    @ExcelIgnore
    //审批状态0:未审批,1审批通过,2审批驳回,3无法审批
    private String approveStatus;
    @ExcelIgnore
    //过期适用标志位0:无,1有
    private String hasExpirationTime;
    @ExcelIgnore
    //发起人ID
    private Long applyBy;
    @ExcelIgnore
    //审批人ID
    private Long approveBy;
}
