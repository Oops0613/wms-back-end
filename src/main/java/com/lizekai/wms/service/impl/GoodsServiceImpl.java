package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.Goods;
import com.lizekai.wms.domain.vo.PageVo;
import com.lizekai.wms.enums.AppHttpCodeEnum;
import com.lizekai.wms.handler.exception.SystemException;
import com.lizekai.wms.mapper.GoodsMapper;
import com.lizekai.wms.service.GoodsMonitorService;
import com.lizekai.wms.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 货物表(Goods)表服务实现类
 *
 * @author makejava
 * @since 2024-12-01 11:27:43
 */
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Autowired
    @Lazy
    private GoodsMonitorService goodsMonitorService;
    @Override
    public ResponseResult getGoodsList(Goods goods, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(goods.getName()), Goods::getName, goods.getName());
        wrapper.eq(!Objects.isNull(goods.getCategoryId()), Goods::getCategoryId, goods.getCategoryId());
        wrapper.orderByDesc(Goods::getUpdateTime);
        Page<Goods> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        page.getRecords().forEach(item->{
            Long goodsId=item.getId();
            String status = goodsMonitorService.getMonitorStatus(goodsId);
            item.setMonitorStatus(status);
        });
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult addGoods(Goods goods) {
        if(existGoodsName(goods.getName())){
            throw new SystemException(AppHttpCodeEnum.GOODS_NAME_EXIST);
        }
        save(goods);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult updateGoods(Goods goods) {
        Goods oldGoods = getById(goods.getId());
        if(!oldGoods.getName().equals(goods.getName())){
            if(existGoodsName(goods.getName())){
                throw new SystemException(AppHttpCodeEnum.GOODS_NAME_EXIST);
            }
        }
        updateById(goods);
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getGoodsById(Long id) {
        return ResponseResult.okResult(getById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult deleteGoods(Long id) {
        Goods goods = getById(id);
        if (!Objects.isNull(goods) && goods.getAmount() > 0) {
            return ResponseResult.errorResult(500, "该货物正在使用中，无法删除");
        }
        //同时删除库存监控表的数据
        goodsMonitorService.deleteMonitorByGoodsId(id);
        removeById(id);
        return ResponseResult.okResult();
    }

    @Override
    public boolean hasGoodsByCategory(Long categoryId) {
        LambdaQueryWrapper<Goods> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getCategoryId, categoryId);
        return count(wrapper) > 0;
    }

    @Override
    public ResponseResult listAllGoods() {
        LambdaQueryWrapper<Goods> wrapper=new LambdaQueryWrapper<>();
        return ResponseResult.okResult(list(wrapper));
    }
    private boolean existGoodsName(String name){
        LambdaQueryWrapper<Goods> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Goods::getName,name);
        return count(wrapper)>0;
    }
}

