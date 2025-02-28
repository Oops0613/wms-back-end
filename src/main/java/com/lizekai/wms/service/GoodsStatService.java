package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.GetAmountChangeDto;
import com.lizekai.wms.domain.entity.Goods;
import org.springframework.stereotype.Service;

@Service
public interface GoodsStatService extends IService<Goods> {
    ResponseResult getAmountChange(GetAmountChangeDto dto);
    ResponseResult getWarningList();
    ResponseResult getGoodsDistribution(Long goodsId);
}
