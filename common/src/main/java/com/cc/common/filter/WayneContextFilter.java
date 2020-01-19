package com.cc.common.filter;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

/**
 * @Author: Wayne
 * @Date: 2019/12/22 13:57
 * @Version: 1.0
 */
public class WayneContextFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(WayneContextFilter.class);

    @Autowired
    ApplicationContext applicationContext;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String, String[]> map = servletRequest.getParameterMap();
        ServletContext servletContext = servletRequest.getServletContext();
        Enumeration<String> attributeNames = servletContext.getAttributeNames();
        System.err.println("context filter is processed..." + JSON.toJSONString(attributeNames));
        logger.debug("我拿不到域对象啊");
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
