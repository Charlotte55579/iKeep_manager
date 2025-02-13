package com.fts.fts.fitness_tracking_system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName fitness_activities
 */
@TableName(value ="fitness_activities")
@Data
public class FitnessActivities {
    /**
     * 
     */
    @TableId(value = "activity_id", type = IdType.AUTO)
    private Integer activityId;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 
     */
    @TableField(value = "activity_type")
    private String activityType;

    /**
     * 
     */
    @TableField(value = "activity_duration")
    private Integer activityDuration;

    /**
     * 
     */
    @TableField(value = "calories_burned")
    private Double caloriesBurned;

    /**
     * 
     */
    @TableField(value = "heart_rate")
    private Integer heartRate;

    /**
     * 
     */
    @TableField(value = "activity_date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date activityDate;

    @TableField(value = "is_flag")
    private Integer isFlag;
}