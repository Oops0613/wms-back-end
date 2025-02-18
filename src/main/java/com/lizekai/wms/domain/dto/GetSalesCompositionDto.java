package com.lizekai.wms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetSalesCompositionDto {
    //时间范围（单位：天）
    private Long days;
    //是否精确到二级分类（0否1是）
    private String isPrecise;
}
