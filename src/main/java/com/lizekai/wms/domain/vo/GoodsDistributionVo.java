package com.lizekai.wms.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDistributionVo {
    //所属仓库id
    private Long warehouseId;
    //仓库名
    private String warehouseName;
    //库存数量
    private Double amount;
}
