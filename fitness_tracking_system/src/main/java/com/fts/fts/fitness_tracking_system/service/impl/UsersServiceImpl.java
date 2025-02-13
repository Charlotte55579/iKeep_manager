package com.fts.fts.fitness_tracking_system.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fts.fts.fitness_tracking_system.mapper.UsersMapper;
import com.fts.fts.fitness_tracking_system.pojo.Users;
import com.fts.fts.fitness_tracking_system.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author huawei
* @description 针对表【users】的数据库操作Service实现
* @createDate 2025-02-05 11:26:21
*/
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Autowired
    private UsersMapper usersMapper;
    @Override
    public Users userLogin(String username, String password)  {
        return usersMapper.selectOne(new QueryWrapper<Users>().eq("username",username).eq("password", MD5.create().digestHex(password)));
    }

    @Override
    public void userRegister(Users user) {
        usersMapper.insert(user);
    }

    @Override
    public Users checkUser(String username) {
        return usersMapper.selectOne(new QueryWrapper<Users>().eq("username",username));
    }

    @Override
    public void updateUser(Users user) {
        usersMapper.updateById(user);
    }

    @Override
    public Users getUserInfo(Integer userId)  {
        return usersMapper.selectById(userId);
    }
}




