package com.example.config;

import cn.dev33.satoken.stp.StpUtil;
import com.example.annotation.NeedAuth;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Component
public class NeedAuthConfig {

    @Pointcut("@annotation(com.example.annotation.NeedAuth)")
    public void needAuth() {
    }

    @Before("needAuth()")
    public void beforeRequestDirect(JoinPoint point) throws IOException {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        NeedAuth annotation = method.getAnnotation(NeedAuth.class);

        if (StpUtil.getLoginIdDefaultNull() == null) {
            // 用户没有token 访问主页
            need("need auto");

        } else {
            // 用户有token 访问主页
            if (annotation.needAuth()) {
                // 服务器拿着的权限
                List<String> roles = StpUtil.getRoleList();
                // controller 方法上的权限
                String role = roles.get(0);
                // 浏览器拿着的权限
                String[] needRole = annotation.needRole();

                if (!role.equals(needRole[0])) {
                    need("need permission");
                }

                /*if (!StpUtil.hasRole(needRole[0])) {
                    need("need permission");
                }*/
            }
        }
    }

    private void need(String msg) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        response.setContentType("application/json;charset=UTF-8");

        ServletOutputStream writer = response.getOutputStream();

        writer.println(msg);

        writer.close();
    }


}
