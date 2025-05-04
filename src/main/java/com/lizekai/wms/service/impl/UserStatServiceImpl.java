package com.lizekai.wms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lizekai.wms.domain.ResponseResult;
import com.lizekai.wms.domain.dto.GetWorkLoadDto;
import com.lizekai.wms.domain.entity.Record;
import com.lizekai.wms.domain.vo.WorkLoadVo;
import com.lizekai.wms.enums.RoleTypeEnum;
import com.lizekai.wms.mapper.RecordMapper;
import com.lizekai.wms.service.UserStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userStatService")
public class UserStatServiceImpl extends ServiceImpl<RecordMapper, Record> implements UserStatService {
    @Autowired
    private RecordMapper recordMapper;
    @Override
    public ResponseResult getWorkLoad(GetWorkLoadDto dto) {
        Long roleId=dto.getRoleId();
        List<WorkLoadVo> vo=new ArrayList<>();
        if(RoleTypeEnum.ROLE_INVENTORY.getCode().equals(roleId)){
            vo=recordMapper.getApplyWorkLoad(dto.getDays());
        } else if (RoleTypeEnum.ROLE_APPROVE.getCode().equals(roleId)) {
            vo=recordMapper.getApproveWorkLoad(dto.getDays());
        }
        return ResponseResult.okResult(vo);
    }
}
