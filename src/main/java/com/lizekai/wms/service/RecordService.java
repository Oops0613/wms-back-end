package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.RecordListDto;
import com.lizekai.wms.domain.entity.Record;
import org.springframework.stereotype.Service;

/**
 * 出入库记录表(Record)表服务接口
 *
 * @author makejava
 * @since 2025-01-13 10:31:58
 */
@Service
public interface RecordService extends IService<Record> {

    ResponseResult getRecordList(RecordListDto dto, Integer pageNum, Integer pageSize);
    //获取入库申请分页列表
    ResponseResult getInApplyList(RecordListDto dto, Integer pageNum, Integer pageSize);
    //获取出库申请分页列表
    ResponseResult getOutApplyList(RecordListDto dto, Integer pageNum, Integer pageSize);
    //获取调拨申请分页列表
    ResponseResult getAllotApplyList(RecordListDto dto, Integer pageNum, Integer pageSize);

    ResponseResult getApplyById(Long id);
}

