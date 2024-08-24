package com.lhxh.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lhxh.demo.interceptors.LoginInterceptor;
import com.lhxh.demo.interceptors.RoleInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    @Autowired
    private LoginInterceptor loginInterceptor;
    @Autowired
    private RoleInterceptor roleInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       //放行登录和注册接口
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register","/captcha");
        //角色拦截器
        registry.addInterceptor(roleInterceptor).addPathPatterns("/user/export/admin","/category/admin","/activity/admin");
    }
}
