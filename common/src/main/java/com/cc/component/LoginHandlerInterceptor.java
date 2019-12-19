package com.cc.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录验证拦截器,注册到webMvcConfigurerAdapter中使用
 * @Author: Wayne
 * @Date: 2019/12/17 11:30
 * @Version: 1.0
 */
public class LoginHandlerInterceptor implements HandlerInterceptor{
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if (loginUser==null){
            //未登录,请求转发登录页面
            request.setAttribute("msg","未登录,请先登录!");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
            //已经登录,放行请求
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
