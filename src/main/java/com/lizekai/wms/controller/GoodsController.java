package com.lizekai.wms.controller;

import com.lizekai.wms.annotation.SystemLog;
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
    @SystemLog(businessName = "分页查询货物列表")
    public ResponseResult getGoodsList(Goods goods,Integer pageNum, Integer pageSize){
        return goodsService.getGoodsList(goods,pageNum,pageSize);
    }
    @GetMapping("/listAll")
    @SystemLog(businessName = "查询所有货物列表")
    public ResponseResult listAllGoods(){
        return goodsService.listAllGoods();
    }
    @PostMapping
    @SystemLog(businessName = "新增货物")
    public ResponseResult addGoods(@RequestBody Goods goods){
        return goodsService.addGoods(goods);
    }
    @DeleteMapping("/{id}")
    @SystemLog(businessName = "删除货物")
    public ResponseResult deleteGoods(@PathVariable(name = "id") Long id){
        return goodsService.deleteGoods(id);
    }
    @PutMapping()
    @SystemLog(businessName = "修改货物")
    public ResponseResult updateGoods(@RequestBody Goods goods){
        return goodsService.updateGoods(goods);
    }
    @GetMapping("/{id}")
    @SystemLog(businessName = "查询货物信息")
    public ResponseResult getGoodsById(@PathVariable("id") Long id){
        return goodsService.getGoodsById(id);
    }
    @GetMapping("/getAmountChange")
    @SystemLog(businessName = "查询货物库存日变化")
    public ResponseResult getAmountChange(GetAmountChangeDto dto){
        return goodsStatService.getAmountChange(dto);
    }
    @GetMapping("/getWarningList")
    @SystemLog(businessName = "查询货物预警列表")
    public ResponseResult getWarningList(){
        return goodsStatService.getWarningList();
    }
    @GetMapping("/getGoodsDistribution/{id}")
    @SystemLog(businessName = "查询货物库存的仓库分布")
    public ResponseResult getGoodsDistribution(@PathVariable("id") Long id){
        return goodsStatService.getGoodsDistribution(id);
    }
    @GetMapping("/getMonitor/{id}")
    @SystemLog(businessName = "查询库存监控信息")
    public ResponseResult getMonitorByGoodsId(@PathVariable("id") Long id){
        return goodsMonitorService.getMonitorByGoodsId(id);
    }
    @PostMapping("/updateMonitor")
    @SystemLog(businessName = "编辑库存监控")
    public ResponseResult updateMonitor(@RequestBody GoodsMonitor goodsMonitor){
        return goodsMonitorService.updateMonitor(goodsMonitor);
    }
}
