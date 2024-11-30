package com.lizekai.wms.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * (UserTest)表实体类
 *
 * @author makejava
 * @since 2024-11-23 10:53:40
 */
@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value="user_test")
public class UserTest {

    private Integer id;
    private String name;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

}

