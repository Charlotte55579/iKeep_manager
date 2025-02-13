package com.fts.fts.fitness_tracking_system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName fitness_goals
 */
@TableName(value ="fitness_goals")
@Data
public class FitnessGoals {
    /**
     * 
     */
    @TableId(value = "goal_id", type = IdType.AUTO)
    private Integer goalId;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 
     */
    @TableField(value = "goal_type")
    private String goalType;

    /**
     * 
     */
    @TableField(value = "goal_value")
    private double goalValue;

    @TableField(value = "actual_value")
    private double actualValue;

    /**
     * 
     */
    @TableField(value = "start_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime startDate;

    @TableField(value = "end_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime endDate;
    /**
     * 
     */
    @TableField(value = "progress")
    private Double progress;

    @TableField(value = "is_notification")
    private Integer isNotification;

    @TableField(value = "is_reminder")
    private Integer isReminder;

    @TableField(value = "is_flag")
    private Integer isFlag;
}