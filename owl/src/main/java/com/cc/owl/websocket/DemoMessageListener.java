package com.cc.owl.websocket;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Wayne
 * @Date: 2020/1/16 22:43
 * @Version: 1.0
 */

public class DemoMessageListener implements MessageListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoMessageListener.class);

    @Autowired
    private DemoWebSocketHandler demoWebSocketHandler;


    @Override
    public void onMessage(Message message) {
        System.out.println(message.getBody());
        LOGGER.info("还款消息体是:" + "sss");
        Gson gson = new Gson();
        Demo demo = gson.fromJson("sss", Demo.class);
        DataVo dataVo = new DataVo();
        dataVo.setType(2);
        dataVo.setDate(demo.getTime());
        dataVo.setValue(demo.getValue());
        dataVo.setName(demo.getName());
        demoWebSocketHandler.send(dataVo);
    }
}