package com.fts.fts.fitness_tracking_system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * 
 * @TableName users
 */
@TableName(value ="users")
@Data
public class Users implements Serializable {
    /**
     * 
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 
     */
    @TableField(value = "username")
    private String username;

    /**
     * 
     */
    @TableField(value = "password")
    private String password;

    /**
     * 
     */
    @TableField(value = "email")
    private String email;

    /**
     * 
     */
    @TableField(value = "weight")
    private BigDecimal weight;

    /**
     * 
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 
     */
    @TableField(value = "fitness_goal")
    private String fitnessGoal;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}