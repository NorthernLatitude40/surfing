package com.cc.owl;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: Wayne
 * @Date: 2019/12/7 15:09
 * @Version: 1.0
 */

public class TestDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "你好啊";
        byte[] bytes = "你好啊".getBytes("ISO-8859-1");
        byte[] bytes2 = "你好啊".getBytes("UTF-8");
        System.out.println("bytes: " + Arrays.toString(bytes));
        System.out.println("bytes2: " + Arrays.toString(bytes2));

        String s = new String(str.getBytes(), "ISO-8859-1");
        String s2 = new String(s.getBytes("UTF-8"), "UTF-8");
        System.out.println("s: " + s);
        System.out.println("s2: " + s2);
    }

    public native void test1();

    @Test
    public void test2() throws InterruptedException {
        final CountDownLatch begin = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(10);
        final ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            final int N0 = i + 1;
            Runnable run =  new Runnable() {
                /**
                 * When an object implementing interface <code>Runnable</code> is used
                 * to create a thread, starting the thread causes the object's
                 * <code>run</code> method to be called in that separately executing
                 * thread.
                 * <p>
                 * The general contract of the method <code>run</code> is that it may
                 * take any action whatsoever.
                 *
                 * @see Thread#run()
                 */
                @Override
                public void run() {
                    try {
                        begin.await();
                        Thread.sleep(1000);
                        System.out.println("N0." + N0 + " arrived");
                    }catch (InterruptedException e ){

                    }finally {
                        end.countDown();
                    }
                }
            };
            exec.submit(run);
        }
        System.out.println("Game Start");
        begin.countDown();
        end.await();
        System.out.println("Game Over");
        exec.shutdown();
    }
}
