package com.fts.fts.fitness_tracking_system.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName notifications
 */
@TableName(value ="notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notifications {
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    /**
     * 信息内容
     */
    @TableField(value = "message")
    private String message;

    /**
     * 信息类别
     */
    @TableField(value = "type")
    private String type;

    /**
     * 信息时间
     */
    @TableField(value = "notification_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime notificationTime;

}