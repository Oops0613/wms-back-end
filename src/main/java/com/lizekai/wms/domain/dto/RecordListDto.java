package com.lizekai.wms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordListDto {
    //源仓库
    private Long fromId;
    //目的仓库
    private Long toId;
    //货物分类
    private Long categoryId;
    //货物名
    private String goodsName;
    //申请类型（1入库2出库3调拨）
    private String type;
}
