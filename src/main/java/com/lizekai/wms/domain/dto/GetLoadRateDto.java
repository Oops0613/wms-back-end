package com.lizekai.wms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetLoadRateDto {
    //所属仓库id
    private Long warehouseId;
    //时间范围（单位：天）
    private Long days;
    //类型（0负载率1负载量）
    private String type;
}
