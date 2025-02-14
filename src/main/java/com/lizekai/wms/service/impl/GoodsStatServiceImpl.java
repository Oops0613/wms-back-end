package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemCanstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.GetAmountChangeDto;
import com.lizekai.wms.domain.entity.Goods;
import com.lizekai.wms.domain.entity.Inventory;
import com.lizekai.wms.domain.entity.Record;
import com.lizekai.wms.domain.vo.GoodsAmountChangeVo;
import com.lizekai.wms.domain.vo.GoodsDistributionVo;
import com.lizekai.wms.domain.vo.GoodsWarningListVo;
import com.lizekai.wms.mapper.GoodsMapper;
import com.lizekai.wms.mapper.InventoryMapper;
import com.lizekai.wms.service.GoodsStatService;
import com.lizekai.wms.service.InventoryService;
import com.lizekai.wms.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service("GoodsStatService")
public class GoodsStatServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsStatService {
    @Autowired
    private RecordService recordService;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private InventoryMapper inventoryMapper;
    @Override
    public ResponseResult getAmountChange(GetAmountChangeDto dto) {
        //获取货物当前的库存数量
        Goods goods = getById(dto.getGoodsId());
        int days = Math.toIntExact(dto.getDays());
        if(days<1){
            return ResponseResult.errorResult(500,"days参数应为正整数");
        }
        //获取该货物过去n天的入库数量日变化列表
        LambdaQueryWrapper<Record> wrapper = new LambdaQueryWrapper<>();
        List<Double> inAmount = new ArrayList<>(Collections.nCopies(days+1, 0.0));//按日期正序排列，最后一个元素为今天
        List<Double> outAmount = new ArrayList<>(Collections.nCopies(days+1, 0.0));
        LocalDateTime fromDate = LocalDate.now().minusDays(days - 1).atStartOfDay();
        LocalDateTime now = LocalDateTime.now();
        wrapper.ge(Record::getApproveTime, fromDate)
                .le(Record::getApproveTime, now)
                .eq(Record::getGoodsId, dto.getGoodsId())
                .eq(Record::getApproveStatus, SystemCanstants.APPROVE_PASS);
        List<Record> recordList = recordService.list(wrapper);//该货物的出入库记录
        recordList.forEach(record -> {
            //日期取整，用于计算日期差
            LocalDate fromDay = record.getApproveTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate today = LocalDate.now();
            int gap = (int) ChronoUnit.DAYS.between(fromDay, today);
            if(SystemCanstants.IN_APPLY.equals(record.getType())){
                inAmount.set(days-gap,record.getAmount());
            }
            else if(SystemCanstants.OUT_APPLY.equals(record.getType())){
                outAmount.set(days-gap,record.getAmount());
            }
        });
        //将两个数据合并累加，计算过去n天的库存数量日变化
        LinkedList<Double> stockList = new LinkedList<>();
        Double amount = goods.getAmount();
        for (int i = 0; i < days; i++) {
            //头插法倒推剩余容量
            amount = amount - inAmount.get(days-i)+outAmount.get(days-i);
            stockList.addFirst(amount);
        }
        //去掉今天的数据（还没过24点不统计）
        inAmount.remove(days);
        outAmount.remove(days);
        GoodsAmountChangeVo vo=new GoodsAmountChangeVo(inAmount,outAmount,stockList);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult getWarningList() {
        List<Goods> lowList=goodsMapper.listInsufficientGoods();
        List<Goods> highList=goodsMapper.listSurplusGoods();
        LambdaQueryWrapper<Inventory> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Inventory::getHasExpirationTime,SystemCanstants.CAN_EXPIRE);
        // 当前时间加上三天（过期预警的标准线）
        LocalDateTime deadline = LocalDateTime.now().plusDays(SystemCanstants.DAYS_TO_EXPIRE);
        wrapper.le(Inventory::getExpirationTime,deadline);
        List<Inventory> expiredList=inventoryService.list(wrapper);
        GoodsWarningListVo vo=new GoodsWarningListVo(lowList,highList,expiredList);
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult getGoodsDistribution(Long goodsId) {
        List<GoodsDistributionVo> vo=inventoryMapper.getGoodsDistribution(goodsId);
        return ResponseResult.okResult(vo);
    }
}
