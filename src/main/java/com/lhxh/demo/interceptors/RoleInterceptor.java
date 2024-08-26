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
        int roleLevel=(int) map.get("rolelevel");
        // 获取请求的URI
        String uri = request.getRequestURI();
        // 设定不同的权限控制
        if (uri.contains("/member") && roleLevel<2) {
            // 如果访问的是/member路径，但用户不是会员，则返回403 Forbidden
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "你没有足够权限");
            return false;
        }
        if (uri.contains("/worker") && roleLevel<3) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "你没有足够权限");
            return false;
        }
        if (uri.contains("/president") && roleLevel<4) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "你没有足够权限");
            return false;
        }
        // 允许访问
        return true;
    }
}