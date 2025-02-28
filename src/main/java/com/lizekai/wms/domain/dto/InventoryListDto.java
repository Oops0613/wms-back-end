package com.lizekai.wms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryListDto {

    //仓库
    private Long warehouseId;
    //货物分类
    private Long categoryId;
    //货物名
    private String goodsName;
    //忽略数量为0的库存（0否1是）
    private String ignoreZero;
}
