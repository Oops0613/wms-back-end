package com.lizekai.wms.domain.entity;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 公告表(Notice)表实体类
 *
 * @author makejava
 * @since 2025-02-24 12:30:28
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="wms_notice")
public class Notice {

    private Long id;
    //公告标题
    private String title;
    //所属分类id
    private String content;
    //备注
    private String remark;

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
    //表中没有的字段
    //接收方角色
    @TableField(exist = false)
    private List<Long> roleList;
    //已读状态（0未读1已读）
    @TableField(exist = false)
    private String isRead;
}

