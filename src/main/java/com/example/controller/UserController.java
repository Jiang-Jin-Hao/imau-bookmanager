package com.example.controller;


import cn.dev33.satoken.context.model.SaRequest;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.example.annotation.NeedAuth;
import com.example.entity.User;
import com.example.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")

//@NeedAuth
// ctrl+alt+o
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    public String login(User user){

        try{

              if (userService.login(user)){

                  user = userService.findUserByName(user);
                  StpUtil.login(user.getId());

                   return SaResult.ok("登录成功").toString();
              }else {
                  return SaResult.error("登录失败").toString();
              }

        }catch (Exception e){
            return "服务器异常";
        }

    }

    @RequestMapping("/hello")

    @NeedAuth(needAuth = true,needRole = {"admin"})
    public String doHello(){

        return "Hello";
    }
}
