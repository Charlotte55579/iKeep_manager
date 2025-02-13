package com.fts.fts.fitness_tracking_system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fts.fts.fitness_tracking_system.pojo.FitnessActivities;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author huawei
* @description 针对表【fitness_activities】的数据库操作Service
* @createDate 2025-02-08 14:03:30
*/
public interface FitnessActivitiesService extends IService<FitnessActivities> {

    IPage<FitnessActivities> getAll(int pageNum,int pageSize,String search,Integer userId);

    void add(FitnessActivities fitnessActivities);

    void update(FitnessActivities fitnessActivities);

    void delete(Integer id);

    Map<String,Object> getData(Integer userId);

    List<FitnessActivities> getFitnessActivitiesByUserId(Integer userId);
}
