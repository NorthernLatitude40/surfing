package com.cc.common.config;

import com.cc.common.filter.WayneContextFilter;
import com.cc.common.component.WayneLocaleResolver;
import edu.emory.mathcs.backport.java.util.Arrays;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Wayne
 * @Date: 2019/12/16 22:41
 * @Version: 1.0
 */
//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc 全面接管SpringMVC
//@ComponentScan(basePackages = {"com.cc"})
//@EnableConfigurationProperties
@MapperScan(basePackages = {"com.cc.common.mapper"})
@Configuration
public class WayneConfig {

//    @Bean(name = "redisTemplate")
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
//        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
//        template.setConnectionFactory(redisConnectionFactory);
//        GenericJackson2JsonRedisSerializer serializer = new GenericJackson2JsonRedisSerializer();
//        template.setDefaultSerializer(serializer);
//        return template;
//    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Primary
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.
                serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())).
                serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<String>(String.class)));
        RedisCacheManager.RedisCacheManagerBuilder redisCacheManagerBuilder = RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory);
        RedisCacheManager.RedisCacheManagerBuilder redisCacheManagerBuilder1 = redisCacheManagerBuilder.cacheDefaults(redisCacheConfiguration);
        RedisCacheManager build = redisCacheManagerBuilder1.build();
        return build;
    }

//    @Primary
//    @Bean
//    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
//        //缓存配置对象
//        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
//
//        redisCacheConfiguration = redisCacheConfiguration
//                //.entryTtl(Duration.ofMinutes(30L)) //设置缓存的默认超时时间：30分钟
//                .disableCachingNullValues()             //如果是空值，不缓存
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))         //设置key序列化器
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer((valueSerializer())));  //设置value序列化器
//
//        return RedisCacheManager
//                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
//                .cacheDefaults(redisCacheConfiguration).build();
//    }
//
//    private RedisSerializer<String> keySerializer() {
//        return new StringRedisSerializer();
//    }
//
//    private RedisSerializer<Object> valueSerializer() {
//        return new GenericJackson2JsonRedisSerializer();
//    }

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
        filter.setOrder(2);
        return filter;
    }

    @Bean
    public FilterRegistrationBean wayneContextFilter() {
        FilterRegistrationBean filter = new FilterRegistrationBean(new WayneContextFilter());
        filter.addUrlPatterns("/*"); //
        //registration.addInitParameter("paramName", "paramValue"); //
        filter.setName("wayneContextFilter");
        filter.setOrder(1);
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
