package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemConstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.GetSalesCompositionDto;
import com.lizekai.wms.domain.dto.GetWarehouseCompositionDto;
import com.lizekai.wms.domain.entity.Inventory;
import com.lizekai.wms.domain.vo.SalesCompositionVo;
import com.lizekai.wms.domain.vo.WarehouseCompositionVo;
import com.lizekai.wms.mapper.InventoryMapper;
import com.lizekai.wms.mapper.RecordMapper;
import com.lizekai.wms.service.CategoryStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("categoryStatService")
public class CategoryStatServiceImpl extends ServiceImpl<InventoryMapper, Inventory> implements CategoryStatService {
    @Autowired
    private InventoryMapper inventoryMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Override
    public ResponseResult getWarehouseComposition(GetWarehouseCompositionDto dto) {
        List<WarehouseCompositionVo> vo;
        if(SystemConstants.IS_PRECISE.equals(dto.getIsPrecise())){
            vo=inventoryMapper.getPreciseWarehouseComposition(dto.getWarehouseId());
        }
        else {
            vo=inventoryMapper.getWarehouseComposition(dto.getWarehouseId());
        }
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult getSalesComposition(GetSalesCompositionDto dto) {
        List<SalesCompositionVo> vo;
        if(SystemConstants.IS_PRECISE.equals(dto.getIsPrecise())){
            vo=recordMapper.getPreciseSalesComposition(dto.getDays());
        }
        else {
            vo=recordMapper.getSalesComposition(dto.getDays());
        }
        return ResponseResult.okResult(vo);
    }
}
