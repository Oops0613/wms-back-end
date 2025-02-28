package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.GetWorkLoadDto;
import com.lizekai.wms.domain.entity.Record;

public interface UserStatService extends IService<Record> {
    ResponseResult getWorkLoad(GetWorkLoadDto dto);
}
