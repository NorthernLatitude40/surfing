package com.cc.owl.websocket;

import org.springframework.stereotype.Component;

/**
 * @Author: Wayne
 * @Date: 2020/1/16 22:42
 * @Version: 1.0
 */
@Component
public class DemoWebSocketHandler extends AbstractWebSocketCommonHandler<DataVo> {

    /**
     * 设置消息返回路由
     *
     * @return
     */
    @Override
    public String setTopic() {
        return "/topic/addLoanPoint";
    }
}