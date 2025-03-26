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
 * 运动记录
 * @TableName fitness_goals
 */
@TableName(value ="fitness_goals")
@Data
public class FitnessGoals {
    /**
     * 主键ID
     */
    @TableId(value = "goal_id", type = IdType.AUTO)
    private Integer goalId;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 目标类型
     */
    @TableField(value = "goal_type")
    private String goalType;

    /**
     * 目标值
     */
    @TableField(value = "goal_value")
    private double goalValue;

    /**
     * 实际值
     */
    @TableField(value = "actual_value")
    private double actualValue;

    /**
     * 开始时间
     */
    @TableField(value = "start_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime startDate;

    /**
     * 结束时间
     */
    @TableField(value = "end_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private LocalDateTime endDate;

    /**
     * 进度
     */
    @TableField(value = "progress")
    private Double progress;

    /**
     * 是否通知
     */
    @TableField(value = "is_notification")
    private Integer isNotification;

    /**
     * 是否提醒
     */
    @TableField(value = "is_reminder")
    private Integer isReminder;

    /**
     * 是否标记
     */
    @TableField(value = "is_flag")
    private Integer isFlag;
}