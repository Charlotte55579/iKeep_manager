package com.fts.fts.fitness_tracking_system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fts.fts.fitness_tracking_system.pojo.FitnessActivities;
import com.fts.fts.fitness_tracking_system.service.FitnessActivitiesService;
import com.fts.fts.fitness_tracking_system.utils.JwtUtil;
import com.fts.fts.fitness_tracking_system.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/fitnessActivities")
public class FitnessActivitiesController {
    @Autowired
    private FitnessActivitiesService fitnessActivitiesService;

    @GetMapping("/getAll")
    public Result<?> getAll(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "") String search,
            @RequestParam Integer userId,
            @RequestHeader("Authorization") String token
            ) {
        try {
            boolean validate = JwtUtil.validateToken(token);
            if (!validate)  {
                return new Result<>(401, "未授权",validate);
            }
            if(userId==null){
                return new Result<>(400,"用户ID不能为空");
            }
            IPage<FitnessActivities> activities=fitnessActivitiesService.getAll(pageNum,pageSize,search,userId);
            return new Result<>(200,"获取成功",activities);
        }catch (Exception e){
            e.printStackTrace();
        }
      return new Result<>(400,"获取失败");
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody FitnessActivities fitnessActivities) {
        try {
            fitnessActivitiesService.add(fitnessActivities);
            return new Result<>(200,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error("添加失败",e.getMessage());
        }
        return new Result<>(400,"添加失败");
    }
    @PostMapping("/update")
    public Result<?> update(@RequestBody FitnessActivities fitnessActivities) {
        try {
            fitnessActivitiesService.update(fitnessActivities);
            return new Result<>(200,"更新成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error("更新失败",e.getMessage());
        }
        return new Result<>(400,"更新失败");
    }
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        try {
            fitnessActivitiesService.delete(id);
            return new Result<>(200,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error("删除失败",e.getMessage());
        }
        return new Result<>(400,"删除失败");
    }
}
