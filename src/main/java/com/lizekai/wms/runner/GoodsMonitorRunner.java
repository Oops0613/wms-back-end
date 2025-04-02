package com.lizekai.wms.runner;

import com.lizekai.wms.domain.entity.GoodsMonitor;
import com.lizekai.wms.job.GoodsMonitorScheduler;
import com.lizekai.wms.service.GoodsMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 项目启动时从数据库批量加载库存监控的定时任务
 */
@Component
public class GoodsMonitorRunner implements CommandLineRunner {
    @Autowired
    private GoodsMonitorService goodsMonitorService;
    @Autowired
    private GoodsMonitorScheduler scheduler;

    @Override
    public void run(String... args) throws Exception {
        List<GoodsMonitor> tasks = goodsMonitorService.listEnabledTasks();
        scheduler.loadTasks(tasks);
    }
}
