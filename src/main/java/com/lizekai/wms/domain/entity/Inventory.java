package com.lizekai.wms.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 库存表(Inventory)表实体类
 *
 * @author makejava
 * @since 2025-01-13 10:25:19
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="wms_inventory")
public class Inventory {

    private Long id;
    //所属货物id
    private Long goodsId;
    //货物名
    private String goodsName;
    //所属仓库id
    private Long warehouseId;
    //仓库名
    private String warehouseName;
    //所属分类id
    private Long categoryId;
    //分类名
    private String categoryName;
    //库存数量
    private Double amount;
    //所占容积
    private Double volume;
    //入库时间
    private Date enterTime;
    //过期适用标志位0:无,1有
    private String hasExpirationTime;
    //过期时间
    private Date expirationTime;
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

}

