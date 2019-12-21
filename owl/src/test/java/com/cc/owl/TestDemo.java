package com.cc.owl;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Properties;

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
        System.out.println("bytes: "+ Arrays.toString(bytes));
        System.out.println("bytes2: "+Arrays.toString(bytes2));

        String s = new String(str.getBytes(),"ISO-8859-1");
        String s2 = new String(s.getBytes("UTF-8"),"UTF-8");
        System.out.println("s: "+s);
        System.out.println("s2: "+s2);
    }

    public native void test1();
}
