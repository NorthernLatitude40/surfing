package com.cc.common.component;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 国际化解析器,注册到webMvcConfigurerAdapter使用
 * @Author: Wayne
 * @Date: 2019/12/17 0:37
 * @Version: 1.0
 */
public class WayneLocaleResolver implements LocaleResolver {
    /**
     * 不以Accept-Language请求头中的本地浏览器语言信息国际化,转而以请求路径参数配置国际化信息
     * @param req
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest req) {
        String l = req.getParameter("l");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)){
            String[] split = l.split("_");
            locale = new Locale(split[0],split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
