package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.InventoryListDto;
import com.lizekai.wms.domain.entity.Inventory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * 库存表(Inventory)表服务接口
 *
 * @author makejava
 * @since 2025-01-13 10:25:55
 */
@Service
public interface InventoryService extends IService<Inventory> {

    ResponseResult getInventoryList(InventoryListDto dto, Integer pageNum, Integer pageSize);

    void export(HttpServletResponse response);
}

