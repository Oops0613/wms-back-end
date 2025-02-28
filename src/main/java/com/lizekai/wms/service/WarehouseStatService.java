package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.GetLoadRateDto;
import com.lizekai.wms.domain.entity.Warehouse;
import org.springframework.stereotype.Service;

/**
 * 负责数据统计-仓库统计部分的业务逻辑
 */
@Service
public interface WarehouseStatService extends IService<Warehouse> {
    ResponseResult GetLoadRate(GetLoadRateDto dto);

    ResponseResult listWarehouseByLoadRate(Double bound);
}
