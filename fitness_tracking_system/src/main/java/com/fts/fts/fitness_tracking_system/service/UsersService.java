package com.fts.fts.fitness_tracking_system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fts.fts.fitness_tracking_system.pojo.Users;


/**
* @author huawei
* @description 针对表【users】的数据库操作Service
* @createDate 2025-02-05 11:26:21
*/
public interface UsersService extends IService<Users> {

    //用户登录
    Users userLogin(String username, String password);

    //用户注册
    void userRegister(Users user);
    //检查用户名是否存在
    Users checkUser(String username);

    //更新用户信息
    void updateUser(Users user);

    Users getUserInfo(Integer userId);
}
