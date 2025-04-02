package com.lizekai.wms.controller;

import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.GetAmountChangeDto;
import com.lizekai.wms.domain.entity.Goods;
import com.lizekai.wms.domain.entity.GoodsMonitor;
import com.lizekai.wms.service.GoodsMonitorService;
import com.lizekai.wms.service.GoodsService;
import com.lizekai.wms.service.GoodsStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private GoodsStatService goodsStatService;
    @Autowired
    private GoodsMonitorService goodsMonitorService;

    @GetMapping("/list")
    public ResponseResult getGoodsList(Goods goods,Integer pageNum, Integer pageSize){
        return goodsService.getGoodsList(goods,pageNum,pageSize);
    }
    @GetMapping("/listAll")
    public ResponseResult listAllGoods(){
        return goodsService.listAllGoods();
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
    @GetMapping("/getAmountChange")
    public ResponseResult getAmountChange(GetAmountChangeDto dto){
        return goodsStatService.getAmountChange(dto);
    }
    @GetMapping("/getWarningList")
    public ResponseResult getWarningList(){
        return goodsStatService.getWarningList();
    }
    @GetMapping("/getGoodsDistribution/{id}")
    public ResponseResult getGoodsDistribution(@PathVariable("id") Long id){
        return goodsStatService.getGoodsDistribution(id);
    }
    @GetMapping("/getMonitor/{id}")
    public ResponseResult getMonitorByGoodsId(@PathVariable("id") Long id){
        return goodsMonitorService.getMonitorByGoodsId(id);
    }
    @PostMapping("/updateMonitor")
    public ResponseResult updateMonitor(@RequestBody GoodsMonitor goodsMonitor){
        return goodsMonitorService.updateMonitor(goodsMonitor);
    }
}
