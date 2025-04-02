package com.lizekai.wms.job;

import com.lizekai.wms.domain.entity.Goods;
import com.lizekai.wms.domain.entity.GoodsMonitor;
import com.lizekai.wms.service.GoodsService;
import com.lizekai.wms.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 库存监控的定时任务类
 */
@Component
public class GoodsMonitorScheduler {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10); // 创建线程池
    private final Map<Long, ScheduledFuture<?>> taskMap = new HashMap<>();  // 用于存储任务的引用

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private EmailUtil emailUtil;
    // 批量启用的任务
    public void loadTasks(List<GoodsMonitor> tasks) {
        for (GoodsMonitor task : tasks) {
            scheduleTask(task);
        }
    }
    // 创建并启动一个定时任务
    private void scheduleTask(GoodsMonitor task) {
        Runnable taskRunnable = () -> checkInventory(task);

        // 使用间隔时间和固定频率来创建定时任务
        ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(taskRunnable, 0, task.getPeriod(), TimeUnit.SECONDS);

        // 将任务添加到 taskMap 中，以便后续管理
        taskMap.put(task.getGoodsId(), future);
    }

    public void updateTask(GoodsMonitor task){
        cancelTask(task.getGoodsId());
        scheduleTask(task);
    }


    // 取消任务
    public void cancelTask(Long goodsId) {
        ScheduledFuture<?> future = taskMap.get(goodsId);
        if (future != null) {
            future.cancel(false); // 取消任务
            taskMap.remove(goodsId); // 从map中移除
        }
    }


    private void checkInventory(GoodsMonitor task) {
        Long goodsId= task.getGoodsId();
        Goods goods = goodsService.getById(goodsId);
        Double lowThreshold = goods.getLowThreshold();
        Double amount = goods.getAmount();
        if(amount<lowThreshold){
            String template="{0}（货物ID：{1}）的低库存阈值为{2}{3}，当前存量为{4}{3}，请及时补货。";
            String title="【缺货预警】"+goods.getName();
            String content= MessageFormat.format(template,goods.getName(),goodsId,lowThreshold,goods.getUnit(),amount);
            emailUtil.sendMessage(task.getEmail(),title,content);
        }

    }


}
