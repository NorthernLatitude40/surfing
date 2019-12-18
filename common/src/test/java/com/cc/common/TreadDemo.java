package com.cc.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Wayne
 * @Date: 2019/12/11 9:28
 * @Version: 1.0
 */
public class TreadDemo {
    public static void firstThread(){
        //创建一个可缓存线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"---firstThreadPool");
                }
            });
        }

    }

    public static void secondThread(){
        //创建一个可重用固定个数线程的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"---secondThreadPool");
                }
            });
        }

    }

    public static void thirdThread(){
        //创建一个定长线程池, 支持定时和延迟执行任务
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
//        for (int i = 0; i < 10; i++) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            scheduledExecutorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getName()+"---thirdThreadPool");
//                }
//            });
//            scheduledExecutorService.schedule(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("延迟5秒执行");
//                }
//            }, 5, TimeUnit.SECONDS);

//        }
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟5秒后每3秒执行一次");
            }
        },5,3,TimeUnit.SECONDS);
    }

    public static void forthThread(){
        //创建一个单线程化的线程池
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+"---forthThreadPool");
                }
            });
        }
    }
    public static void main(String[] args) {
        thirdThread();
    }
}
