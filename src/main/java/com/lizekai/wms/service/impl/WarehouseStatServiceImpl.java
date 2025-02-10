package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemCanstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.GetLoadRateDto;
import com.lizekai.wms.domain.entity.Record;
import com.lizekai.wms.domain.entity.Warehouse;
import com.lizekai.wms.mapper.WarehouseMapper;
import com.lizekai.wms.service.RecordService;
import com.lizekai.wms.service.WarehouseStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service("WarehouseStatService")
public class WarehouseStatServiceImpl extends ServiceImpl<WarehouseMapper, Warehouse> implements WarehouseStatService {
    @Autowired
    private WarehouseMapper warehouseMapper;
    @Autowired
    private RecordService recordService;
    @Override
    public ResponseResult GetLoadRate(GetLoadRateDto dto) {
        //获取仓库当前的使用情况
        Warehouse warehouse = getById(dto.getWarehouseId());
        int days = Math.toIntExact(dto.getDays());
        if(days<1){
            return ResponseResult.errorResult(500,"days参数应为正整数");
        }
        //获取该仓库过去n天的入库容量日变化列表
        LambdaQueryWrapper<Record> wrapper = new LambdaQueryWrapper<>();
        double[] inVolume = new double[days];//按日期倒序排列，第0个元素为今天
        LocalDateTime fromDate = LocalDate.now().minusDays(days - 1).atStartOfDay();
        LocalDateTime now = LocalDateTime.now();
        wrapper.ge(Record::getApproveTime, fromDate)
                .le(Record::getApproveTime, now)
                .eq(Record::getToId, dto.getWarehouseId())
                .eq(Record::getApproveStatus, SystemCanstants.APPROVE_PASS);
        List<Record> inList = recordService.list(wrapper);
        inList.forEach(record -> {
            //日期取整，用于计算日期差
            LocalDate fromDay = record.getApproveTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate today = LocalDate.now();
            int gap = (int) ChronoUnit.DAYS.between(fromDay, today);
            inVolume[gap] += record.getVolume();
        });
        //获取该仓库过去n天的出库容量日变化列表
        double[] outVolume = new double[days];
        wrapper.clear();//清空之前的查询条件
        wrapper.ge(Record::getApproveTime, fromDate)
                .le(Record::getApproveTime, now)
                .eq(Record::getFromId, dto.getWarehouseId())
                .eq(Record::getApproveStatus, SystemCanstants.APPROVE_PASS);
        List<Record> outList = recordService.list(wrapper);
        outList.forEach(record -> {
            //日期取整，用于计算日期差
            LocalDate fromDay = record.getApproveTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate today = LocalDate.now();
            int gap = (int) ChronoUnit.DAYS.between(fromDay, today);
            outVolume[gap] += record.getVolume();
        });
        //将两个数据合并累加，计算过去n天的剩余容量日变化
        LinkedList<Double> volumeList = new LinkedList<>();
        Double remainingCapacity = warehouse.getRemainingCapacity();
        for (int i = 0; i < days; i++) {
            //头插法倒推剩余容量
            remainingCapacity = remainingCapacity + inVolume[i] - outVolume[i];
            volumeList.addFirst(remainingCapacity);
        }
        double maxCapacity = warehouse.getCapacity();
        List<Double> resultList;
        if (SystemCanstants.LOAD_RATE.equals(dto.getType())) {
            resultList = volumeList.stream()
                    .map(v -> 1 - v / maxCapacity)
                    .collect(Collectors.toList());
        } else {
            resultList = volumeList.stream()
                    .map(v -> maxCapacity - v)
                    .collect(Collectors.toList());
        }
        return ResponseResult.okResult(resultList);
    }
    /**
     * 查询负载率低于/高于bound的仓库
     *
     * @param bound（-100~100）（正数：查询大于bound的；负数：查询小于-bound的）
     * @return
     */
    @Override
    public ResponseResult listWarehouseByLoadRate(Double bound) {
        if(bound<-100||bound>100){
            return ResponseResult.errorResult(500,"bound参数越界，合法范围=[-100,100]");
        }
        boolean negative = false;
        if (bound <= 0) {
            negative = true;
            bound = -bound;
        }
        //计算闲置率（0~1）
        double remainingRate = (100 - bound) / 100;
        List<Warehouse> warehouseList;
        if (negative) {
            warehouseList = warehouseMapper.listWarehouseRRateBiggerThan(remainingRate);
        } else {
            warehouseList = warehouseMapper.listWarehouseRRateSmallerThan(remainingRate);
        }
        return ResponseResult.okResult(warehouseList);
    }
}
