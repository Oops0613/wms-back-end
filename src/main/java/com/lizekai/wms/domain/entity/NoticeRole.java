package com.lizekai.wms.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 公告和收件角色关联表(NoticeRole)表实体类
 *
 * @author makejava
 * @since 2025-02-24 12:32:34
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="wms_notice_role")
public class NoticeRole {
    //公告ID
    private Long noticeId;
    //角色ID
    private Long roleId;

}

