package com.lizekai.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizekai.wms.domain.entity.Inventory;
import com.lizekai.wms.domain.vo.GoodsDistributionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {
    @Select(" SELECT warehouse_id,warehouse_name,SUM(amount) AS amount " +
            " FROM wms_inventory " +
            " WHERE goods_id=#{goodsId} " +
            " GROUP BY warehouse_id,warehouse_name " +
            " ORDER BY amount DESC " +
            " LIMIT 20")
    List<GoodsDistributionVo> getGoodsDistribution(Long goodsId);
}
