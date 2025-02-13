package com.fts.fts.fitness_tracking_system.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fts.fts.fitness_tracking_system.controller.EventController;
import com.fts.fts.fitness_tracking_system.mapper.NotificationsMapper;
import com.fts.fts.fitness_tracking_system.pojo.FitnessGoals;
import com.fts.fts.fitness_tracking_system.pojo.Notifications;
import com.fts.fts.fitness_tracking_system.service.FitnessGoalsService;
import com.fts.fts.fitness_tracking_system.mapper.FitnessGoalsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author huawei
* @description 针对表【fitness_goals】的数据库操作Service实现
* @createDate 2025-02-09 11:51:10
*/
@Service
@Slf4j
public class FitnessGoalsServiceImpl extends ServiceImpl<FitnessGoalsMapper, FitnessGoals>
    implements FitnessGoalsService{
    private static final String MESSAGE_COMPLETED = "恭喜您已完成健身目标！";
    private static final String MESSAGE_CLOSE = "您已接近健身目标，加油！";
    @Autowired
    private FitnessGoalsMapper fitnessGoalsMapper;
    @Autowired
    private EventController eventController;
    @Autowired
    private NotificationsMapper notificationsMapper;
    @Override
    public IPage<FitnessGoals> getAll(int pageNum, int pageSize, String search, Integer userId) {
        Page<FitnessGoals> page = new Page<>(pageNum,pageSize);
        QueryWrapper<FitnessGoals> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_flag",0);
        queryWrapper.eq("user_id",userId);
        if(StrUtil.isNotEmpty(search)){
            queryWrapper.like("goal_type",search).or().like("goal_value",search)
                    .or().like("start_date",search).or().like("end_date",search);
        }
        return fitnessGoalsMapper.selectPage(page,queryWrapper);
    }

    @Override
    public void add(FitnessGoals fitnessGoals) {
        fitnessGoalsMapper.insert(fitnessGoals);
    }

    @Override
    public void update(FitnessGoals fitnessGoals) {
        try{
            Integer userId = fitnessGoalsMapper.selectById(fitnessGoals.getGoalId()).getUserId();
            Integer goalId=fitnessGoalsMapper.selectById(fitnessGoals.getGoalId()).getGoalId();
            log.info("userId:{}",userId);
            fitnessGoals.setProgress(fitnessGoals.getProgress());
            fitnessGoalsMapper.updateById(fitnessGoals);
            log.info("userId after update:{}",userId);
            checkGoalProgress(userId,goalId);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Integer id) {
        FitnessGoals fitnessGoals=new FitnessGoals();
        fitnessGoals.setIsFlag(1);
        fitnessGoalsMapper.update(fitnessGoals,new QueryWrapper<FitnessGoals>().eq("goal_id",id));
    }


    @Override
    public void checkGoalProgress(Integer userId,Integer goalId) {
        List<FitnessGoals> fitnessGoalsList = fitnessGoalsMapper.selectList(new QueryWrapper<FitnessGoals>().eq("user_id", userId).eq("is_flag", 0)
                .ne("is_notification",2));
        Map<String, List<FitnessGoals>> groupedByProgress = fitnessGoalsList.stream()
                .filter(goal -> goal.getGoalValue() != 0)
                .collect(Collectors.groupingBy(
                        goal -> {
                            double progress = (goal.getActualValue() / goal.getGoalValue()) * 100;
                            return progress == 100 ? "completed" : (progress >= 90 ? "close" : "other");
                        }
                ));

        // 发送通知
        if (groupedByProgress.containsKey("completed")) {
            eventController.notify(userId, MESSAGE_COMPLETED);
            FitnessGoals fitnessGoals = fitnessGoalsMapper.selectOne(new QueryWrapper<FitnessGoals>().eq("progress",100).eq("user_id",userId).eq("is_flag",0)
                    .eq("is_notification",1));
            fitnessGoals.setIsNotification(2);
            fitnessGoalsMapper.update(fitnessGoals,new QueryWrapper<FitnessGoals>().eq("user_id",userId)
                    .eq("is_flag",0).eq("is_notification",1).eq("progress",100).eq("goal_id",goalId));
            if(fitnessGoals.getIsNotification()==2){
                Notifications notifications = new Notifications();
                notifications.setUserId(userId);
                notifications.setMessage(MESSAGE_COMPLETED);
                notifications.setType("目标完成");
                notifications.setNotificationTime(LocalDateTime.now());
                notificationsMapper.insert(notifications);
            }
        }

        if (groupedByProgress.containsKey("close")) {
            eventController.notify(userId, MESSAGE_CLOSE);
            FitnessGoals fitnessGoals = fitnessGoalsMapper.selectOne(new QueryWrapper<FitnessGoals>().gt("progress",90).eq("user_id",userId).eq("is_flag",0)
                    .eq("is_notification",0).or().eq("progress",90));
            fitnessGoals.setIsNotification(1);
            fitnessGoalsMapper.update(fitnessGoals,new QueryWrapper<FitnessGoals>().eq("user_id",userId).eq("is_flag",0)
                    .eq("is_notification",0).gt("progress",90).eq("goal_id",goalId).or().eq("progress",90));
            if(fitnessGoals.getIsNotification()==1){
                Notifications notifications = new Notifications();
                notifications.setUserId(userId);
                notifications.setMessage(MESSAGE_CLOSE);
                notifications.setType("接近目标");
                notifications.setNotificationTime(LocalDateTime.now());
                notificationsMapper.insert(notifications);
            }
        }
    }

    @Override
    public void checkGoalTime(Integer userId){
        List<FitnessGoals> fitnessGoals=fitnessGoalsMapper.selectList(new QueryWrapper<FitnessGoals>().eq("user_id",userId).eq("is_flag",0)
                .eq("is_reminder",0));
        try{
            for(FitnessGoals goal:fitnessGoals){
                DateTime startDateTime = new DateTime(goal.getStartDate());
                DateTime endDateTime = new DateTime(goal.getEndDate());
                DateTime nowDateTime = new DateTime();
                if(nowDateTime.isAfter(startDateTime) && nowDateTime.isBefore(endDateTime)){
                    eventController.notify(userId,"您已进入健身目标时间！");
                    Notifications notifications = new Notifications();
                    notifications.setUserId(userId);
                    notifications.setMessage("您已进入健身目标时间！");
                    notifications.setType("提醒");
                    notifications.setNotificationTime(LocalDateTime.now());
                    notificationsMapper.insert(notifications);
                    goal.setIsReminder(1);
                    fitnessGoalsMapper.update(goal,new UpdateWrapper<FitnessGoals>().eq("user_id",userId).eq("goal_id",goal.getGoalId()));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        }
}




