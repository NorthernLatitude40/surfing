package com.cc.owl.controller;

import com.cc.exception.UserNotExistException;
//import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常处理器
 * @Author: Wayne
 * @Date: 2019/12/18 23:28
 * @Version: 1.0
 */
@ControllerAdvice
public class WayneExceptionHandler {
    //指定处理哪个异常
    @ExceptionHandler(UserNotExistException.class)
    public String handleException(Exception e, HttpServletRequest req){
        req.setAttribute("javax.servlet.error.status_code",500);
        Map<String, Object> map = new HashMap<>();
        map.put("code", "user.notexist");
        map.put("message", e.getMessage());
        req.setAttribute("extErr",map);
        return "forward:/error";
    }
}
