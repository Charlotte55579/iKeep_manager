package com.fts.fts.fitness_tracking_system.service;

import com.fts.fts.fitness_tracking_system.pojo.Notifications;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author huawei
* @description 针对表【notifications】的数据库操作Service
* @createDate 2025-02-11 14:14:00
*/
public interface NotificationsService extends IService<Notifications> {
    List<Notifications> getNotificationsByUserId(Integer userId);

    void deleteNotifications(Integer id);
}
