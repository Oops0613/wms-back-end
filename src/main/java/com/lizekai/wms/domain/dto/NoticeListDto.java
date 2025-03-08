package com.lizekai.wms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeListDto {
    //查询关键词
    private String keyWord;
    //是否只展示未读公告
    private String unread;
}
