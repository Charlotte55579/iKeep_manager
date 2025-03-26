package com.fts.fts.fitness_tracking_system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @TableName users
 */
@TableName(value ="users")
@Data
public class Users implements Serializable {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;


    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 体重
     */
    @TableField(value = "weight")
    private BigDecimal weight;

    /**
     * 身高
     */
    @TableField(value = "height")
    private BigDecimal height;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 目标
     */
    @TableField(value = "fitness_goal")
    private String fitnessGoal;

}