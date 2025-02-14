package com.lizekai.wms.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsAmountChangeVo {
    //入库数量日变化列表
    List<Double> inList;
    //出库数量日变化列表
    List<Double> outList;
    //库存数量日变化列表
    List<Double> stockList;
}
