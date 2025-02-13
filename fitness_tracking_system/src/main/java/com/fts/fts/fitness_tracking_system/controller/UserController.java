package com.fts.fts.fitness_tracking_system.controller;

import cn.hutool.crypto.digest.MD5;
import com.fts.fts.fitness_tracking_system.pojo.Users;
import com.fts.fts.fitness_tracking_system.service.impl.UsersServiceImpl;
import com.fts.fts.fitness_tracking_system.utils.Result;
import com.fts.fts.fitness_tracking_system.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UsersServiceImpl usersService;

    @PostMapping("/login")
    public Result<?> userLogin(@RequestParam String username, @RequestParam String password) {
      Users users= usersService.userLogin(username,password);
      if(users!=null){
          String token = JwtUtil.generateToken(username,users.getUserId());
          HashMap<String,Object> userMap=new HashMap<>();
          userMap.put("userId",users.getUserId());
          userMap.put("username",username);
          HashMap<String,Object> map=new HashMap<>();
          map.put("token",token);
          map.put("user",userMap);
         return new Result<>(200,"登录成功",map);
      }
      return new Result<>(400,"登录失败");
    }
    @PostMapping("/register")
    public Result<?> registerUser(@RequestBody Users user) {
        // 检查用户名是否已存在
        Users existingUser = usersService.checkUser(user.getUsername());
        if (existingUser != null) {
            return new Result<>(400, "用户名已存在");
        }

        // 加密密码并保存用户
        user.setPassword(MD5.create().digestHex(user.getPassword()));
        usersService.userRegister(user);
        return new Result<>(200, "注册成功");
    }
    @GetMapping("/info/{userId}")
    public Result<?> getUserInfo(@PathVariable Integer userId,@RequestHeader("Authorization") String token) {
        boolean validate = JwtUtil.validateToken(token);
        if (!validate)  {
            return new Result<>(401, "未授权");
        }
        Users user = usersService.getUserInfo(userId);
        if (user == null) {
            return new Result<>(400, "用户不存在");
        }
        return new Result<>(200, "获取成功", user);
    }

    @PostMapping("/update")
    public Result<?> updateUser(@RequestBody Users user) {
        try {
            usersService.updateUser(user);
        } catch (Exception e) {
            return new Result<>(400, "更新失败");
        }
        return new Result<>(200, "更新成功");
    }
}
