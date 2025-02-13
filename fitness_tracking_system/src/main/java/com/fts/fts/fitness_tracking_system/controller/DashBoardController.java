package com.fts.fts.fitness_tracking_system.controller;

import com.fts.fts.fitness_tracking_system.service.impl.FitnessActivitiesServiceImpl;
import com.fts.fts.fitness_tracking_system.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/dashboard")
public class DashBoardController {
    @Autowired
    private FitnessActivitiesServiceImpl fitnessActivitiesService;
    @RequestMapping("/statics/{userId}")
    public Result<?> getDashboard(@PathVariable Integer userId){
        HashMap<String,Object> map=new HashMap<>();
        map.put("dataTrend",fitnessActivitiesService.getData(userId));
        map.put("activitiesData",fitnessActivitiesService.getFitnessActivitiesByUserId(userId));
        return new Result<>(200,"success",map);
    }
}
