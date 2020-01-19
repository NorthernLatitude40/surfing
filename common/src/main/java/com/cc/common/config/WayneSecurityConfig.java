//package com.cc.common.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.core.io.support.SpringFactoriesLoader;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;
//
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
///**
// * @Author: Wayne
// * @Date: 2019/12/22 21:33
// * @Version: 1.0
// */
//@EnableWebSecurity
//public class WayneSecurityConfig extends WebSecurityConfigurerAdapter {
//    /**
//     * 授权规则
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/index","/hello2","/websocket/**").permitAll()
//                .antMatchers("/employee/**").hasRole("VIP1");
//        http.formLogin();
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll();
//        //开启自动配置的注销功能
//        http.logout();
//    }
//
//    /**
//     * 认证规则
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("wayne").password("{noop}123123").roles("VIP1")
//                .and().withUser("zhangsan").password("{noop}123123").roles("VIP2");
//    }
//
//    /**
//     * 认证规则2
//     * @return
//     */
////    @Bean
////    @Override
////    protected UserDetailsService userDetailsService() {
////        UserDetails user =
////                User.withDefaultPasswordEncoder()
////                        .username("user")
////                        .password("password")
////                        .roles("VIP1")
////                        .build();
////
////        return new InMemoryUserDetailsManager(user);
////    }
//
//}
