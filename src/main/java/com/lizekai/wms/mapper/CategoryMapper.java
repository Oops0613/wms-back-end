package com.lizekai.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizekai.wms.domain.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    @Select(" SELECT * " +
            " FROM wms_category " +
            " WHERE parent_id > 0 AND del_flag=0 " +
            " UNION " +
            " SELECT c1.* " +
            " FROM wms_category c1 " +
            " WHERE c1.parent_id = -1 AND c1.del_flag=0 " +
            "  AND EXISTS " +
            "  ( " +
            "    SELECT 1 " +
            "    FROM wms_category c2 " +
            "    WHERE c2.parent_id = c1.id AND c2.del_flag=0 " +
            "  );")
    List<Category> listAvailableCategory();
}
