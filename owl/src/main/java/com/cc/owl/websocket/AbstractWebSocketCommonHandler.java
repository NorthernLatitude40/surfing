package com.cc.owl.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.StringUtils;

public abstract class AbstractWebSocketCommonHandler<T> implements WebSocketCommonHandler<T> {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 设置消息返回路由
     *
     * @return
     */
    public abstract String setTopic();

    /**
     * WebSocket发送消息方法
     *
     * @param o
     */
    public void send(T o) {
        String topic = setTopic();
        if (StringUtils.isEmpty(topic) || o == null) {
            throw new RuntimeException("Topic is Empty or Object is null!");
        }
        this.template.convertAndSend(topic, o);
    }
}