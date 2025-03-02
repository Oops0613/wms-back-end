package com.lizekai.wms.runner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lizekai.wms.constants.SystemCanstants;
import com.lizekai.wms.domain.entity.ReadStatus;
import com.lizekai.wms.service.ReadStatusService;
import com.lizekai.wms.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 项目启动时进行的预处理操作
 */
@Component
public class ReadStatusRunner implements CommandLineRunner {
    @Autowired
    private ReadStatusService readStatusService;
    @Autowired
    private RedisCache redisCache;
    @Override
    public void run(String... args) throws Exception {
        LambdaQueryWrapper<ReadStatus> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(ReadStatus::getIsRead, SystemCanstants.IS_UNREAD);
        List<ReadStatus> readStatusList = readStatusService.list(wrapper);
        //将未读记录整理成map
        //key=userId value=noticeId的集合
        Map<Long, Set<Long>> map = readStatusList.stream()
                .collect(Collectors.groupingBy(
                        ReadStatus::getUserId,
                        Collectors.mapping(ReadStatus::getNoticeId, Collectors.toSet())));

        map.forEach((userId,noticeIds)-> redisCache.setCacheSet("WMSUnread:" + userId,noticeIds));
    }
}
