package com.fts.fts.fitness_tracking_system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fts.fts.fitness_tracking_system.pojo.FitnessGoals;
import com.fts.fts.fitness_tracking_system.service.FitnessGoalsService;
import com.fts.fts.fitness_tracking_system.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fitnessGoals")
@Slf4j
public class FitnessGoalsController {

    @Autowired
    private FitnessGoalsService fitnessGoalsService;

    @GetMapping("/getAll")
    public Result<?> getAll(  @RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam Integer userId) {
        if(userId==null){
            return new Result<>(400,"用户ID不能为空");
        }
        IPage<FitnessGoals> fitnessGoalsIPage=fitnessGoalsService.getAll(pageNum,pageSize,search,userId);

        return new Result<>(200,"获取成功",fitnessGoalsIPage);
    }

    @PostMapping("/add")
    public Result<?> add(@RequestBody FitnessGoals fitnessGoals) {
        try {
            fitnessGoalsService.add(fitnessGoals);
            return new Result<>(200,"添加成功");
        }catch (Exception e){
            e.printStackTrace();
            log.error("添加失败",e.getMessage());
        }
        return new Result<>(400,"添加失败");

    }
    @PostMapping("/update")
    public Result<?> update(@RequestBody FitnessGoals fitnessGoals) {
        try {
            fitnessGoalsService.update(fitnessGoals);
            return new Result<>(200,"更新成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result<>(400,"更新失败");
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        try {
            fitnessGoalsService.delete(id);
            return new Result<>(200,"删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result<>(400,"删除失败");
    }

    @RequestMapping("/checkGoalTime/{userId}")
    public Result<?> checkGoalTime(@PathVariable Integer userId){
        try{
            fitnessGoalsService.checkGoalTime(userId);
            return new Result<>(200,"success");
        }catch (Exception e){
            e.printStackTrace();
        }
       return new Result<>(400,"error");
    }
}
