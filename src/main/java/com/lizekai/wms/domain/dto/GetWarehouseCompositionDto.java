package com.lizekai.wms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetWarehouseCompositionDto {
    //所属仓库id
    private Long warehouseId;
    //是否精确到二级分类（0否1是）
    private String isPrecise;
}
