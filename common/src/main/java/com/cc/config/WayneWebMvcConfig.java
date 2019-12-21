package com.cc.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Author: Wayne
 * @Date: 2019/12/22 0:57
 * @Version: 1.0
 */
public class WayneWebMvcConfig implements WebMvcConfigurer {
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        FastJsonHttpMessageConverter fjc = new FastJsonHttpMessageConverter();
//        FastJsonConfig fj = new FastJsonConfig();
//        fj.setSerializerFeatures(SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteNullListAsEmpty,
//                SerializerFeature.WriteNullStringAsEmpty,
//                SerializerFeature.WriteNullBooleanAsFalse,
//                SerializerFeature.WriteMapNullValue);
//        fjc.setFastJsonConfig(fj);
//        converters.add(fjc);
//    }
}
