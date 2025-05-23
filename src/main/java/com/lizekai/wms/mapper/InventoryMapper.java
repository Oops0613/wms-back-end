package com.lizekai.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizekai.wms.domain.entity.Inventory;
import com.lizekai.wms.domain.vo.GoodsDistributionVo;
import com.lizekai.wms.domain.vo.WarehouseCompositionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface InventoryMapper extends BaseMapper<Inventory> {
    @Select(" SELECT warehouse_id,warehouse_name,SUM(amount) AS amount " +
            " FROM wms_inventory " +
            " WHERE goods_id=#{goodsId} AND amount>0 " +
            " GROUP BY warehouse_id,warehouse_name " +
            " ORDER BY amount DESC " +
            " LIMIT 20")
    List<GoodsDistributionVo> getGoodsDistribution(Long goodsId);
    @Select("<script>" +
            " SELECT ctg.parent_id AS category_id,parent_ctg.name AS category_name,SUM(ivt.volume) AS volume " +
            " FROM wms_inventory AS ivt " +
            " INNER JOIN wms_category AS ctg ON ivt.category_id = ctg.id " +
            " LEFT JOIN wms_category AS parent_ctg ON ctg.parent_id = parent_ctg.id "+
            "<where>" +
            "  ivt.amount>0 " +
            "   <if test='w_id > 0'>" +
            "       AND ivt.warehouse_id = #{w_id} " +
            "   </if>" +
            "</where>" +
            " GROUP BY ctg.parent_id" +
            "</script>")
    List<WarehouseCompositionVo> getWarehouseComposition(Long warehouseId);
    @Select("<script>" +
            " SELECT category_id,category_name,SUM(volume) AS volume " +
            " FROM wms_inventory " +
            "<where>" +
            " amount>0 " +
            "   <if test='w_id > 0'>" +
            "       AND warehouse_id = #{w_id} " +
            "   </if>" +
            "</where>" +
            " GROUP BY category_id,category_name" +
            "</script>")
    List<WarehouseCompositionVo> getPreciseWarehouseComposition(Long warehouseId);
}
