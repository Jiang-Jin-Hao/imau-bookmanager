package com.example.service;

import cn.hutool.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.RoleDao;
import com.example.dao.UserDao;
import com.example.entity.Role;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

// 事务
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    public boolean login(User user) {

        User dbUser = findUserByName(user);
        if (dbUser == null) {
            return false;
        } else {
            if (user.getPassword().equals(dbUser.getPassword())) {

                /*// 密钥
                byte[] key = "IMAU".getBytes();
                String token = JWT.create()
                        .setPayload("id", dbUser.getId())
                        .setPayload("username", dbUser.getUsername())
                        .setKey(key)
                        .sign();*/



                return true;
            } else {
                return false;
            }
        }

    }

    public String reg(User user) {

        User resultUser = findUserByName(user);

        if (resultUser != null) {
            return "用户名已存在";


        } else {
            userDao.insert(user);

//            new Role()
//            roleDao.insert()

            return "注册成功";
        }

    }

    public User findUserByName(User user) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());

        return userDao.selectOne(queryWrapper);

    }

}
