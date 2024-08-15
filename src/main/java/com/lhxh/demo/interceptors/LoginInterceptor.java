package com.lhxh.demo.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.lhxh.demo.pojo.Result;
import com.lhxh.demo.utils.JwtUtil;
import com.lhxh.demo.utils.ThreadLocalUtil;

@Component
public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //令牌验证
        String token = request.getHeader("Authorization");
        //验证token
        try{
            Map<String,Object> claims = JwtUtil.parseToken(token);
            //放行
            //把业务数据存到ThreadLocal中
            ThreadLocalUtil.set(claims);
            return true;
        } catch(Exception e)
        {
            //设置http响应状态码401
            response.setStatus(401);
            //不放行
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
        //清空ThreadLocal数据
        ThreadLocalUtil.remove();
    }
}
