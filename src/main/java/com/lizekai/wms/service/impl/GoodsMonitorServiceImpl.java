package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemConstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.enums.AppHttpCodeEnum;
import com.lizekai.wms.job.GoodsMonitorScheduler;
import com.lizekai.wms.mapper.GoodsMonitorMapper;
import com.lizekai.wms.domain.entity.GoodsMonitor;
import com.lizekai.wms.service.GoodsMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * 库存监控表(GoodsMonitor)表服务实现类
 *
 * @author makejava
 * @since 2025-03-31 12:18:33
 */
@Service("goodsMonitorService")
public class GoodsMonitorServiceImpl extends ServiceImpl<GoodsMonitorMapper, GoodsMonitor> implements GoodsMonitorService {
    @Autowired
    private GoodsMonitorScheduler scheduler;
    @Override
    public List<GoodsMonitor> listEnabledTasks() {
        LambdaQueryWrapper<GoodsMonitor> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(GoodsMonitor::getStatus, SystemConstants.TASK_ON);
        return list(wrapper);
    }

    @Override
    public ResponseResult updateMonitor(GoodsMonitor goodsMonitor) {
        String status = goodsMonitor.getStatus();
        if(SystemConstants.TASK_OFF.equals(status)){
            scheduler.cancelTask(goodsMonitor.getGoodsId());
            saveOrUpdate(goodsMonitor);
        } else if (SystemConstants.TASK_ON.equals(status)) {
            scheduler.updateTask(goodsMonitor);
            saveOrUpdate(goodsMonitor);
        }
        else {
            return ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR,"status值有误");
        }
        return ResponseResult.okResult();
    }

    @Override
    public ResponseResult getMonitorByGoodsId(Long id) {
        LambdaQueryWrapper<GoodsMonitor> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(GoodsMonitor::getGoodsId,id);
        return ResponseResult.okResult(getOne(wrapper));
    }

    @Override
    public boolean deleteMonitorByGoodsId(Long id) {
        LambdaQueryWrapper<GoodsMonitor> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(GoodsMonitor::getGoodsId,id);
        return remove(wrapper);
    }

    @Override
    public String getMonitorStatus(Long goodsId) {
        LambdaQueryWrapper<GoodsMonitor> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(GoodsMonitor::getGoodsId,goodsId);
        GoodsMonitor monitor = getOne(wrapper);
        if(Objects.nonNull(monitor)){
            return monitor.getStatus();
        }
        return SystemConstants.TASK_OFF;
    }
}

