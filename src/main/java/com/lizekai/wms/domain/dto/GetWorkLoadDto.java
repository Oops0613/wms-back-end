package com.lizekai.wms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetWorkLoadDto {
    //角色ID
    private Long roleId;
    //时间范围（单位：天）
    private Long days;
}
