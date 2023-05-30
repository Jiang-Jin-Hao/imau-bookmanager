package com.example.service;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dao.RoleDao;
import com.example.dao.UserDao;
import com.example.entity.Record;
import com.example.entity.Role;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component

// SaToken

public class StpInterfaceIml implements StpInterface {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {

        User user = userDao.selectById((String) loginId);

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_user_name", user.getUsername());
        Role role = roleDao.selectOne(queryWrapper);

        ArrayList<String> list = new ArrayList<>();
        if (role.getRoleName().equals("admin")) {
            list.add("admin");

        } else if (role.getRoleName().equals("user")) {
            list.add("user");
        }

        return list;
    }
}
