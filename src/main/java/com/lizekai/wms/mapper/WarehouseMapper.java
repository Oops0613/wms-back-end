package com.lizekai.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizekai.wms.domain.entity.Warehouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface WarehouseMapper extends BaseMapper<Warehouse> {
    @Select("SELECT * FROM wms_warehouse WHERE remaining_capacity/capacity>=#{rate}")
    List<Warehouse> listWarehouseRRateBiggerThan(Double rate);
    @Select("SELECT * FROM wms_warehouse WHERE remaining_capacity/capacity<=#{rate}")
    List<Warehouse> listWarehouseRRateSmallerThan(Double rate);
}
