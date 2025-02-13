package com.fts.fts.fitness_tracking_system.mapper;

import com.fts.fts.fitness_tracking_system.pojo.Notifications;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author huawei
* @description 针对表【notifications】的数据库操作Mapper
* @createDate 2025-02-11 14:14:00
* @Entity com.fts.fts.fitness_tracking_system.pojo.Notifications
*/
public interface NotificationsMapper extends BaseMapper<Notifications> {

    void saveBatch(List<Notifications> notifications);
}




