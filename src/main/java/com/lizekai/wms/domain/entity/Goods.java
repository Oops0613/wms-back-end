package com.lizekai.wms.domain.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 货物表(Goods)表实体类
 *
 * @author makejava
 * @since 2024-12-01 11:26:23
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="wms_goods")
public class Goods {

    private Long id;
    //货物名
    private String name;
    //所属分类id
    private Long categoryId;
    //库存数量
    private Double amount;
    //计量单位
    private String unit;
    //单位价格
    private Double pricePerUnit;
    //每单位所占容积
    private Double volumePerUnit;
    //低库存阈值
    private Double lowThreshold;
    //高库存阈值
    private Double highThreshold;
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
    //备注
    private String remark;
    //过期适用标志（0无1有）
    private String hasExpirationTime;

}

