package com.example.aop;

import cn.hutool.jwt.JWTUtil;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//@Aspect
//@Component
public class ControllerAOP {

//    @Pointcut("@annotation(com.example.annotation.NeedAuth)")
    public void doAuth() {

    }

//    @Before("doAuth()")
    public void doBefore() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader("Token");
        if (token == null) {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            PrintWriter writer = null;
            try {
                writer = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            writer.println("请登录后再尝试");
        } else {
            boolean b = JWTUtil.verify(token, "IMAU".getBytes());
        }
    }


}
