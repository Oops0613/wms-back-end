package com.lizekai.wms.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTestDto {
    Long pageNum;
    Long pageSize;
    String name;
    String order;
}
