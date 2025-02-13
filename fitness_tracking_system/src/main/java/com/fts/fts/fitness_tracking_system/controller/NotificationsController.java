package com.fts.fts.fitness_tracking_system.controller;

import com.fts.fts.fitness_tracking_system.pojo.Notifications;
import com.fts.fts.fitness_tracking_system.service.NotificationsService;
import com.fts.fts.fitness_tracking_system.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationsController {

    @Autowired
    private NotificationsService notificationsService;

    @GetMapping("/getNotificationsByUserId/{userId}")
    public Result<?> getNotificationsByUserId(@PathVariable Integer userId){
        try{
            List<Notifications> notificationsList=notificationsService.getNotificationsByUserId(userId);
            return new Result<>(200,"success",notificationsList);
        }catch (Exception e){
            return new Result<>(400,"error",e.getMessage());
        }
    }

    @PostMapping("/deleteNotifications/{id}")
    public Result<?> deleteNotifications(@PathVariable Integer id){
        notificationsService.deleteNotifications(id);
        return new Result<>(200,"success");
    }

}
