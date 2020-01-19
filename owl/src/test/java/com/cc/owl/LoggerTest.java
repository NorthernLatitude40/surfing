package com.cc.owl;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: Wayne
 * @Date: 2019/12/16 15:29
 * @Version: 1.0
 */
@SpringBootTest
public class LoggerTest {
    Logger logger = LoggerFactory.getLogger(getClass());
    @org.junit.Test
    public void loggerDemo(){
        //日志级别由低到高 trace<debug<info<warn<error五个级别
        //可以调整输出的日志级别,日志就会在这个级别和更高的级别生效
        logger.trace("这是trace级别日志");
        logger.debug("这是debug级别日志");
        logger.info("这是info级别日志");
        logger.warn("这是warn级别日志");
        logger.error("这是error级别日志");
        long s = System.currentTimeMillis();
        ArrayList<String> list = new ArrayList<>();

        int[] i = new int[10000];
        for (int j = 0; j < i.length; j++) {
            i[j] = j;
            list.add(""+j);
        }

        long e = System.currentTimeMillis();
        for (String s1:
                list) {
            System.out.println(s1);
        }
        System.out.println(Arrays.toString(i)+"-------"+(e));
        System.out.println(s);
    }
}
