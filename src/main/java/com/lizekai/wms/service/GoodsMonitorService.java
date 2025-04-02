package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.entity.GoodsMonitor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 库存监控表(GoodsMonitor)表服务接口
 *
 * @author makejava
 * @since 2025-03-31 12:18:32
 */
@Service
public interface GoodsMonitorService extends IService<GoodsMonitor> {
    List<GoodsMonitor> listEnabledTasks();

    ResponseResult updateMonitor(GoodsMonitor goodsMonitor);

    ResponseResult getMonitorByGoodsId(Long id);

    String getMonitorStatus(Long goodsId);
}

