package com.lhxh.demo.interceptors;

import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.lhxh.demo.utils.ThreadLocalUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RoleInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取当前用户角色
        Map<String,Object> map= ThreadLocalUtil.get();
        String userRole=(String) map.get("role");
        // 获取请求的URI
        String uri = request.getRequestURI();
        // 设定不同的权限控制
        if (uri.endsWith("/admin") && !"管理员".equals(userRole)) {
            // 如果访问的是/admin路径，但用户不是管理员，则返回403 Forbidden
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "你没有足够权限");
            return false;
        }
        // 允许访问
        return true;
    }
}