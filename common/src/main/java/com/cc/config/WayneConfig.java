package com.cc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.cc.component.LoginHandlerInterceptor;
import com.cc.component.WayneLocaleResolver;
import com.sun.beans.WeakCache;
import com.sun.org.apache.xpath.internal.SourceTree;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Wayne
 * @Date: 2019/12/16 22:41
 * @Version: 1.0
 */
//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc 全面接管SpringMVC
//@ComponentScan(basePackages = {"com.cc"})
//@EnableConfigurationProperties
@MapperScan(basePackages = {"com.cc.mapper"})
@Configuration
public class WayneConfig {
    @Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }

//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DruidDataSource druidDataSource(){
//        return  new DruidDataSource();
//    }
//
//    //配置Druid的监控
//    //1、配置一个管理后台的Servlet
//    @Bean
//    public ServletRegistrationBean statViewServlet(){
//        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        Map<String,String> initParams = new HashMap<>();
//
//        initParams.put("loginUsername","admin");
//        initParams.put("loginPassword","123456");
//        initParams.put("allow","");//默认就是允许所有访问
//        initParams.put("deny","192.168.15.21");
//
//        bean.setInitParameters(initParams);
//        return bean;
//    }
//
//
//    //2、配置一个web监控的filter
//    @Bean
//    public FilterRegistrationBean webStatFilter(){
//        String arr[] = {"/*"};
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.setFilter(new WebStatFilter());
//
//        Map<String,String> initParams = new HashMap<>();
//        initParams.put("exclusions","*.js, *.css, /druid/*");
//
//        bean.setInitParameters(initParams);
//
//        bean.setUrlPatterns(Arrays.asList(arr));
//
//        return  bean;
//    }

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
//                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                        .excludePathPatterns("/index.html","/index.htm","/index","/","/user/login");
            }
        };
        return webMvcConfigurerAdapter;
    }

    /**
     * 注册国际化解析器
     * @return
     */
    @Bean
    public LocaleResolver localeResolver(){
        return new WayneLocaleResolver();
    }

    @Bean
    public ServletRegistrationBean wayneServlet(){
        return new ServletRegistrationBean(new WayneServlet(), "/servlet");
    }

    @Bean
    public FilterRegistrationBean wayneFilter(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("/hello2");
        strings.add("/servlet");
        String arr[] = {"/hello2","/servlet"};
        List list = Arrays.asList(arr);
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new WayneFilter());
        filter.setUrlPatterns(strings);
        return filter;
    }

    @Bean
    public ServletListenerRegistrationBean wayneListener(){
        return new ServletListenerRegistrationBean(new WayneListener());
    }

    private class WayneServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            this.doPost(req,resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.getWriter().write("Hello WayneServlet");
        }
    }

    private class WayneFilter implements javax.servlet.Filter {
        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            System.out.println("MyFilter process...");
            filterChain.doFilter(servletRequest,servletResponse);
        }

        @Override
        public void destroy() {

        }
    }

    private class WayneListener implements ServletContextListener {
        @Override
        public void contextInitialized(ServletContextEvent sce) {
            System.out.println("contextInitialized...web应用启动");
        }

        @Override
        public void contextDestroyed(ServletContextEvent sce) {
            System.out.println("contextDestroyed...web应用停止");
        }
    }
}
