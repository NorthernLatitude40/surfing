package com.cc.owl.websocket;

public interface WebSocketCommonHandler<T> {

    /**
     * WebSocket发送消息方法
     *
     * @param t
     */
    void send(T t);
}