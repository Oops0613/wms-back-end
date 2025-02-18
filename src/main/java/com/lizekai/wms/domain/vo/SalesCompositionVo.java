package com.lizekai.wms.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalesCompositionVo {
    //所属分类id
    private Long categoryId;
    //分类名
    private String categoryName;
    //销售量
    private Double sales;
}
