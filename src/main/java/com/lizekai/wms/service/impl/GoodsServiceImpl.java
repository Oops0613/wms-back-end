package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.mapper.GoodsMapper;
import com.lizekai.wms.domain.entity.Goods;
import com.lizekai.wms.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * 货物表(Goods)表服务实现类
 *
 * @author makejava
 * @since 2024-12-01 11:27:43
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

}

