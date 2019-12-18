package com.cc.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Wayne
 * @Date: 2019/12/11 10:54
 * @Version: 1.0
 */
public class ThreadPoolCreate {
    public static void main(String[] args) {
        //创建数组型缓冲等待队列
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(10);
        //ThreadPoolExecutor创建自定义线程池,池中保存的线程数为3, 允许最大线程数为6
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3,6,50, TimeUnit.MILLISECONDS,queue);
        //创建三个线程任务
        ThreadDemo threadDemo1 = new ThreadDemo();
        ThreadDemo threadDemo2 = new ThreadDemo();
        ThreadDemo threadDemo3 = new ThreadDemo();
        poolExecutor.execute(threadDemo1);
        poolExecutor.execute(threadDemo2);
        poolExecutor.execute(threadDemo3);
        poolExecutor.shutdown();
    }
}
