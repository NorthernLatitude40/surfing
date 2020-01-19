package com.cc.owl.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

import static jodd.util.StringUtil.join;

/**
 * @Author: Wayne
 * @Date: 2019/12/3 9:57
 * @Version: 1.0
 */
@ServerEndpoint("/websocket/{sid}")
@Component
public class WebSocketServer {
    static Log log= LogFactory.getLog(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //接收sid
    private String sid="";

    private String option;

    public void addData(){
        List<Integer> date = new ArrayList<>();
        List<Double> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Date now = new Date();

            date.add(now.getHours());
            data.add(now.getYear()*Math.random()*150);
            this.option = "{\n" +
                    "    \"xAxis\": {\n" +
                    "        \"type\": \"category\",\n" +
                    "        \"boundaryGap\": false,\n" +
                    "        \"data\":" + date + "},\n" +
                    "    \"yAxis\": {\n" +
                    "        \"boundaryGap\": [\n" +
                    "            0,\n" +
                    "            \"50%\"\n" +
                    "        ],\n" +
                    "        \"type\": \"value\"\n" +
                    "    },\n" +
                    "    \"series\": [\n" +
                    "        {\n" +
                    "            \"name\": \"成交\",\n" +
                    "            \"type\": \"line\",\n" +
                    "            \"smooth\": true,\n" +
                    "            \"symbol\": \"none\",\n" +
                    "            \"stack\": \"a\",\n" +
                    "            \"areaStyle\": {\n" +
                    "                \"normal\": {}\n" +
                    "            },\n" +
                    "            \"data\":" + data + "\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";
            try {
                Thread.sleep(1000);
                sendMessage(option);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session,@PathParam("sid") String sid) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        log.info("有新窗口开始监听:"+sid+",当前在线人数为" + getOnlineCount());
        this.sid=sid;
            this.addData();
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口"+sid+"的信息:"+message);
        //群发消息
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    public void sendMessage(Object message) throws IOException {
        try {
            this.session.getBasicRemote().sendObject(message);
        } catch (EncodeException e) {
            e.printStackTrace();
        }
    }

    /**
     * 群发自定义消息
     * */
    public static void sendInfo(String message,@PathParam("sid") String sid) throws IOException {
        log.info("推送消息到窗口"+sid+"，推送内容:"+message);
        for (WebSocketServer item : webSocketSet) {
            try {
                //这里可以设定只推送给这个sid的，为null则全部推送
                if(sid==null) {
                    item.sendMessage(message);
                }else if(item.sid.equals(sid)){
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

}
