package com.lizekai.wms.domain.vo;

import com.lizekai.wms.domain.entity.Goods;
import com.lizekai.wms.domain.entity.Inventory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsWarningListVo {
    //库存不足的货物列表
    List<Goods> lowList;
    //库存积压的货物列表
    List<Goods> highList;
    //临近过期的库存列表
    List<Inventory> expiredList;
}
