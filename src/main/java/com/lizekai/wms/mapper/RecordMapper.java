package com.lizekai.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizekai.wms.domain.entity.Record;
import com.lizekai.wms.domain.vo.SalesCompositionVo;
import com.lizekai.wms.domain.vo.WorkLoadVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecordMapper extends BaseMapper<Record> {
    @Select("<script>" +
            " SELECT ctg.parent_id AS category_id, parent_ctg.name AS category_name, SUM(rcd.amount*gds.price_per_unit) AS sales " +
            " FROM wms_record AS rcd " +
            " INNER JOIN wms_goods AS gds ON rcd.goods_id = gds.id " +
            " INNER JOIN wms_category AS ctg ON rcd.category_id = ctg.id " +
            " LEFT JOIN wms_category AS parent_ctg ON ctg.parent_id = parent_ctg.id "+
            "<where>" +
            " type=2 AND approve_status=1  " +
            "   <if test='days > 0'>" +
            "       AND approve_time>= CURDATE() - INTERVAL #{days} DAY " +
            "   </if>" +
            "</where>" +
            " GROUP BY ctg.parent_id" +
            "</script>")
    List<SalesCompositionVo> getSalesComposition(Long days);
    @Select("<script>" +
            " SELECT rcd.category_id,rcd.category_name,SUM(rcd.amount*gds.price_per_unit) AS sales " +
            " FROM wms_record AS rcd " +
            " INNER JOIN wms_goods AS gds ON rcd.goods_id = gds.id " +
            "<where>" +
            " type=2 AND approve_status=1 " +
            "   <if test='days > 0'>" +
            "       AND approve_time>= CURDATE() - INTERVAL #{days} DAY " +
            "   </if>" +
            "</where>" +
            " GROUP BY category_id,category_name " +
            "</script>")
    List<SalesCompositionVo> getPreciseSalesComposition(Long days);
    @Select("<script>" +
            " SELECT u.id, u.real_name AS real_name , COUNT(*) AS amount " +
            " FROM wms_record AS rcd " +
            " INNER JOIN sys_user AS u ON rcd.apply_by = u.id " +
            "<where>" +
            " u.role_id in (1,4) " +
            "   <if test='days > 0'>" +
            "       AND apply_time>= CURDATE() - INTERVAL #{days} DAY " +
            "   </if>" +
            "</where>" +
            " GROUP BY apply_by " +
            "</script>")
    List<WorkLoadVo> getApplyWorkLoad(Long days);
    @Select("<script>" +
            " SELECT u.id, u.real_name AS real_name , COUNT(*) AS amount " +
            " FROM wms_record AS rcd " +
            " INNER JOIN sys_user AS u ON rcd.approve_by = u.id " +
            "<where>" +
            " u.role_id in (1,5) " +
            "   <if test='days > 0'>" +
            "       AND approve_time>= CURDATE() - INTERVAL #{days} DAY " +
            "   </if>" +
            "</where>" +
            " GROUP BY approve_by " +
            "</script>")
    List<WorkLoadVo> getApproveWorkLoad(Long days);
}
