package com.cc.component;

//import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 给容器中加入自定义的异常信息
 * @Author: Wayne
 * @Date: 2019/12/18 23:53
 * @Version: 1.0
 */
@Component
public class WayneErrorAttributes extends DefaultErrorAttributes {
//    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        errorAttributes.put("company", "Wayne");
        Map<String, Object> extErr = (Map<String, Object>) webRequest.getAttribute("extErr", 0);
        errorAttributes.put("extErr",extErr);
        return errorAttributes;
    }
}
