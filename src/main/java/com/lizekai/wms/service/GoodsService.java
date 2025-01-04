package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.Category;
import com.lizekai.wms.domain.entity.Goods;
import org.springframework.stereotype.Service;

/**
 * 货物表(Goods)表服务接口
 *
 * @author makejava
 * @since 2024-12-01 11:27:43
 */
@Service
public interface GoodsService extends IService<Goods> {
    ResponseResult getGoodsList(Goods goods,Integer pageNum, Integer pageSize);
    ResponseResult addGoods(Goods goods);

    ResponseResult updateGoods(Goods goods);

    ResponseResult getGoodsById(Long id);

    ResponseResult deleteGoods(Long id);
    //某个分类下是否有货物
    boolean hasGoodsByCategory(Long categoryId);
}

