package com.lizekai.wms.controller;

import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.Goods;
import com.lizekai.wms.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @GetMapping("/list")
    public ResponseResult getGoodsList(Goods goods,Integer pageNum, Integer pageSize){
        return goodsService.getGoodsList(goods,pageNum,pageSize);
    }
    @PostMapping
    public ResponseResult addGoods(@RequestBody Goods goods){
        return goodsService.addGoods(goods);
    }
    @DeleteMapping("/{id}")
    public ResponseResult deleteGoods(@PathVariable(name = "id") Long id){
        return goodsService.deleteGoods(id);
    }
    @PutMapping()
    public ResponseResult updateGoods(@RequestBody Goods goods){
        return goodsService.updateGoods(goods);
    }
    @GetMapping("/{id}")
    public ResponseResult getGoodsById(@PathVariable("id") Long id){
        return goodsService.getGoodsById(id);
    }
}
