package com.fts.fts.fitness_tracking_system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fts.fts.fitness_tracking_system.pojo.FitnessActivities;
import com.fts.fts.fitness_tracking_system.service.FitnessActivitiesService;
import com.fts.fts.fitness_tracking_system.mapper.FitnessActivitiesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
* @author huawei
* @description 针对表【fitness_activities】的数据库操作Service实现
* @createDate 2025-02-08 14:03:30
*/
@Service
public class FitnessActivitiesServiceImpl extends ServiceImpl<FitnessActivitiesMapper, FitnessActivities> implements FitnessActivitiesService{

    @Autowired
    private FitnessActivitiesMapper fitnessActivitiesMapper;
    @Override
    public IPage<FitnessActivities> getAll(int pageNum, int pageSize, String search, Integer userId) {
        Page<FitnessActivities> page = new Page<>(pageNum,pageSize);
        QueryWrapper<FitnessActivities> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_flag",0);
        queryWrapper.eq("user_id",userId);
        if(StrUtil.isNotEmpty(search)){
            queryWrapper.like("activity_type",search).or().like("activity_duration",search)
                    .or().like("activity_date",search)
                    .or().like("heart_rate",search)
                    .or().like("calories_burned",search);
        }
        return fitnessActivitiesMapper.selectPage(page,queryWrapper);

    }

    @Override
    public void add(FitnessActivities fitnessActivities) {
        fitnessActivitiesMapper.insert(fitnessActivities);
    }

    @Override
    public void update(FitnessActivities fitnessActivities) {
        fitnessActivitiesMapper.updateById(fitnessActivities);
    }

    @Override
    public void delete(Integer id) {
        FitnessActivities fitnessActivities=new FitnessActivities();
        fitnessActivities.setIsFlag(1);
        fitnessActivitiesMapper.update(fitnessActivities,new QueryWrapper<FitnessActivities>().eq("activity_id",id));
    }

    @Override
    public Map<String,Object> getData(Integer userId) {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);
        List<FitnessActivities> fitnessActivitiesList = fitnessActivitiesMapper.selectList(new QueryWrapper<FitnessActivities>().eq("user_id",userId).between("activity_date",startOfDay,endOfDay));
        double caloriesSum=0;
        Integer activityTime=0;
        for (FitnessActivities fitnessActivities : fitnessActivitiesList) {
            caloriesSum+=fitnessActivities.getCaloriesBurned();
            activityTime+=fitnessActivities.getActivityDuration();
        }
        Map<String,Object> map = new HashMap<>();
        map.put("caloriesSum",caloriesSum);
        map.put("activityTime",activityTime);
        return map;
    }

    @Override
    public List<FitnessActivities> getFitnessActivitiesByUserId(Integer userId) {
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(7);
        LocalDateTime startOfSevenDaysAgo = sevenDaysAgo.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);
        QueryWrapper<FitnessActivities> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId)
                .between("activity_date", startOfSevenDaysAgo, endOfDay)
                .groupBy("activity_date", "activity_type")
                .orderBy(true, true, "activity_date");

        List<FitnessActivities> fitnessActivitiesList = fitnessActivitiesMapper.selectList(queryWrapper).stream()
                .limit(7)
                .collect(Collectors.toList());

        return fitnessActivitiesList;
    }
}




