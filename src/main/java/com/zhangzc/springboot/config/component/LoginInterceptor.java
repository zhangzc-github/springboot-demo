package com.zhangzc.springboot.config.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("user");
        if (user != null) return true;
        request.setAttribute("msg", "请先登录!");
        request.getRequestDispatcher("/").forward(request, response);
        //response.sendRedirect("/");
        return false;
    }
}
