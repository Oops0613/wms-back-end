package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.constants.SystemCanstants;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.RecordListDto;
import com.lizekai.wms.domain.entity.Inventory;
import com.lizekai.wms.domain.vo.PageVo;
import com.lizekai.wms.mapper.RecordMapper;
import com.lizekai.wms.domain.entity.Record;
import com.lizekai.wms.service.RecordService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 出入库记录表(Record)表服务实现类
 *
 * @author makejava
 * @since 2025-01-13 10:31:58
 */
@Service("recordService")
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {

    @Override
    public ResponseResult getRecordList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Record> wrapper=new LambdaQueryWrapper<>();
        Long fromId=dto.getFromId();
        Long toId=dto.getToId();
        String type=dto.getType();
        wrapper.eq(Objects.nonNull(fromId),Record::getFromId,fromId);
        wrapper.eq(Objects.nonNull(toId),Record::getToId,toId);
        if(StringUtils.hasText(type)){
            if(SystemCanstants.IN_APPLY.equals(type)){
                wrapper.eq(Record::getFromId,-1);
            }
            else if(SystemCanstants.OUT_APPLY.equals(type)){
                wrapper.eq(Record::getToId,-1);
            }
            else if(SystemCanstants.ALLOT_APPLY.equals(type)){
                wrapper.gt(Record::getFromId,0);
                wrapper.gt(Record::getToId,0);
            }
        }
        Page<Record> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult getInApplyList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        Long toId=dto.getToId();
        Long categoryId=dto.getCategoryId();
        String goodsName=dto.getGoodsName();
        LambdaQueryWrapper<Record> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Record::getFromId,-1);
        wrapper.eq(Objects.nonNull(toId),Record::getToId,toId);
        wrapper.eq(Objects.nonNull(categoryId),Record::getCategoryId,categoryId);
        wrapper.like(StringUtils.hasText(goodsName),Record::getGoodsName,goodsName);
        Page<Record> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult getOutApplyList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        String goodsName=dto.getGoodsName();
        Long fromId=dto.getFromId();
        Long categoryId=dto.getCategoryId();
        LambdaQueryWrapper<Record> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(Objects.nonNull(fromId),Record::getFromId,fromId);
        wrapper.eq(Record::getToId,-1);
        wrapper.eq(Objects.nonNull(categoryId),Record::getCategoryId,categoryId);
        wrapper.like(StringUtils.hasText(goodsName),Record::getGoodsName,goodsName);
        Page<Record> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }

    @Override
    public ResponseResult getAllotApplyList(RecordListDto dto, Integer pageNum, Integer pageSize) {
        Long toId=dto.getToId();
        Long fromId=dto.getFromId();
        Long categoryId=dto.getCategoryId();
        String goodsName=dto.getGoodsName();
        LambdaQueryWrapper<Record> wrapper=new LambdaQueryWrapper<>();
        wrapper.gt(Record::getFromId,0);
        wrapper.gt(Record::getToId,0);
        wrapper.eq(Objects.nonNull(fromId),Record::getFromId,fromId);
        wrapper.eq(Objects.nonNull(toId),Record::getToId,toId);
        wrapper.eq(Objects.nonNull(categoryId),Record::getCategoryId,categoryId);
        wrapper.like(StringUtils.hasText(goodsName),Record::getGoodsName,goodsName);
        Page<Record> page = new Page<>(pageNum, pageSize);
        page(page, wrapper);
        return ResponseResult.okResult(new PageVo(page.getRecords(), page.getTotal()));
    }
}

