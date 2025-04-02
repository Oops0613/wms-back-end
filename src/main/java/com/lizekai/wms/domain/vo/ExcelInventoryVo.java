package com.lizekai.wms.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelInventoryVo {
    @ExcelProperty("库存ID")
    private Long id;
    @ExcelProperty("货物ID")
    private Long goodsId;
    @ExcelProperty("货物名")
    @ColumnWidth(20)
    private String goodsName;
    @ExcelProperty("仓库ID")
    private Long warehouseId;
    @ExcelProperty("仓库名")
    @ColumnWidth(20)
    private String warehouseName;
    @ExcelProperty("分类ID")
    private Long categoryId;
    @ExcelProperty("分类名")
    @ColumnWidth(20)
    private String categoryName;
    @ExcelProperty("库存数量")
    private Double amount;
    @ExcelProperty("所占容积（升）")
    @ColumnWidth(20)
    private Double volume;
    @ExcelProperty("入库时间")
    @ColumnWidth(20)
    private Date enterTime;
    @ExcelProperty("过期时间")
    @ColumnWidth(20)
    private Date expirationTime;
    @ExcelProperty("创建人")
    private String createByName;
    @ExcelProperty("创建时间")
    @ColumnWidth(20)
    private Date createTime;
    @ExcelProperty("更新人")
    private String updateByName;
    @ExcelProperty("更新时间")
    @ColumnWidth(20)
    private Date updateTime;

    @ExcelIgnore
    private Long createBy;
    @ExcelIgnore
    private Long updateBy;
    @ExcelIgnore
    //过期适用标志位0:无,1有
    private String hasExpirationTime;
}
