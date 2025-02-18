package com.lizekai.wms.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseCompositionVo {
    //所属分类id
    private Long categoryId;
    //分类名
    private String categoryName;
    //所占容量
    private Double volume;
}
