package com.lizekai.wms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.AddApplyDto;
import com.lizekai.wms.domain.dto.ApproveDto;
import com.lizekai.wms.domain.dto.RecordListDto;
import com.lizekai.wms.domain.entity.Record;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * 出入库记录表(Record)表服务接口
 *
 * @author makejava
 * @since 2025-01-13 10:31:58
 */
@Service
public interface RecordService extends IService<Record> {

    void refreshVolume();

    ResponseResult getRecordList(RecordListDto dto, Integer pageNum, Integer pageSize);
    //获取入库申请分页列表
    ResponseResult getInApplyList(RecordListDto dto, Integer pageNum, Integer pageSize);
    //获取出库申请分页列表
    ResponseResult getOutApplyList(RecordListDto dto, Integer pageNum, Integer pageSize);
    //获取调拨申请分页列表
    ResponseResult getAllotApplyList(RecordListDto dto, Integer pageNum, Integer pageSize);

    ResponseResult getApplyById(Long id);

    ResponseResult addInApply(AddApplyDto dto);

    ResponseResult addOutApply(AddApplyDto dto);

    ResponseResult addAllotApply(AddApplyDto dto);

    ResponseResult approvePass(ApproveDto dto);
    ResponseResult approveReject(ApproveDto dto);

    ResponseResult preApprove();

    void export(HttpServletResponse response);
}

