package com.xm.mall.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huyan
 * @date 2019-04-10 14:42
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginURL = "/tologin";
        // 判断地址是否需要拦截，判断请求的URL与设定的是否相同
        if(request.getServletPath().startsWith(loginURL)){
            //放行
            return true;
        }

        //如果用户已经登录
        if (request.getSession().getAttribute("username")!=null){
            return  true;
        }
        //非法请求,重定向到登录界面
        response.sendRedirect("/WEB-INF/jsp/login.jsp");
        //拦截
        return false;
    }

}
