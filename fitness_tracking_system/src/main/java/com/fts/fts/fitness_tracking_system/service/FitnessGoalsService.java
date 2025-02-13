package com.fts.fts.fitness_tracking_system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fts.fts.fitness_tracking_system.pojo.FitnessGoals;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author huawei
* @description 针对表【fitness_goals】的数据库操作Service
* @createDate 2025-02-09 11:51:11
*/
public interface FitnessGoalsService extends IService<FitnessGoals> {

    IPage<FitnessGoals> getAll(int pageNum, int pageSize, String search, Integer userId);

    void add(FitnessGoals fitnessGoals);

    void update(FitnessGoals fitnessGoals);

    void delete(Integer id);

    void checkGoalProgress(Integer userId, Integer goalId);

    void checkGoalTime(Integer userId);
}
