package com.lizekai.wms.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 公告已读状态表(ReadStatus)表实体类
 *
 * @author makejava
 * @since 2025-02-24 13:01:13
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="wms_read_status")
public class ReadStatus {
    //主键ID
    private Long id;
    //公告ID
    private Long noticeId;
    //用户ID
    private Long userId;
    //是否已读0:未读,1已读
    private String isRead;

}

