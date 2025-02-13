package com.fts.fts.fitness_tracking_system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fts.fts.fitness_tracking_system.pojo.Notifications;
import com.fts.fts.fitness_tracking_system.service.NotificationsService;
import com.fts.fts.fitness_tracking_system.mapper.NotificationsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author huawei
* @description 针对表【notifications】的数据库操作Service实现
* @createDate 2025-02-11 14:14:00
*/
@Service
public class NotificationsServiceImpl extends ServiceImpl<NotificationsMapper, Notifications>
    implements NotificationsService{

    @Autowired
    private NotificationsMapper notificationsMapper;
    @Override
    public List<Notifications> getNotificationsByUserId(Integer userId) {
        return notificationsMapper.selectList(new QueryWrapper<Notifications>().eq("user_id",userId));
    }

    @Override
    public void deleteNotifications(Integer id) {
        notificationsMapper.deleteById(id);
    }
}




