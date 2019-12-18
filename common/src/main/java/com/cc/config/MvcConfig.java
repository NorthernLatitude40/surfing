package com.cc.config;

import com.cc.component.LoginHandlerInterceptor;
import com.cc.component.WayneLocaleResolver;
import com.sun.beans.WeakCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: Wayne
 * @Date: 2019/12/16 22:41
 * @Version: 1.0
 */
//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc 全面接管SpringMVC
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
    //所有的WebMvcConfigurerAdapter组件都会一起起作用
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter webMvcConfigurerAdapter= new WebMvcConfigurerAdapter(){
            //注册视图解析器
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/index.htm").setViewName("index");
                registry.addViewController("/index").setViewName("index");
                registry.addViewController("/main.html").setViewName("dashboard");
            }

            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                        .excludePathPatterns("/index.html","/index.htm","/index","/","/user/login");
            }
        };
        return webMvcConfigurerAdapter;
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new WayneLocaleResolver();
    }
}
