package com.lizekai.wms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lizekai.wms.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Update("update sys_user set password=#{pwd} where id=#{id}")
    void updatePasswordById(@Param("id") Long id,@Param("pwd") String pwd);
}
