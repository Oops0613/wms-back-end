package com.lizekai.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizekai.wms.domain.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    //获取库存不足的货物
    @Select("SELECT * FROM wms_goods WHERE amount<low_threshold")
    List<Goods> listInsufficientGoods();
    //获取库存过剩的货物
    @Select("SELECT * FROM wms_goods WHERE amount>high_threshold")
    List<Goods> listSurplusGoods();
}
