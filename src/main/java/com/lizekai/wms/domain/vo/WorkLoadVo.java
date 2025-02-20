package com.lizekai.wms.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkLoadVo {
    //用户ID
    Long id;
    //用户姓名
    String realName;
    //操作次数
    Long amount;
}
