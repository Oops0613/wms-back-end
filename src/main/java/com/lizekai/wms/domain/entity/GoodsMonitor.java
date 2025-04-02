package com.lizekai.wms.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 库存监控表(GoodsMonitor)表实体类
 *
 * @author makejava
 * @since 2025-04-01 11:33:57
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="wms_goods_monitor")
public class GoodsMonitor {
    //主键
    private Long id;
    //货物ID
    private Long goodsId;
    //监控间隔（秒）
    private Long period;
    //被通知人邮箱
    private String email;
    //任务状态（1正常 0停用）
    private String status;

}

