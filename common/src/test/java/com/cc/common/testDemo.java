package com.cc.common;

import edu.princeton.cs.algs4.*;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author: Wayne
 * @Date: 2019/12/7 18:06
 * @Version: 1.0
 */
public class testDemo {
    public static void main(String[] args) {
        int[] whiteList = In.readInts(args[0]);
        Arrays.sort(whiteList);
        StdOut.println(Arrays.toString(whiteList));
        while (!StdIn.isEmpty()){
            //读取键值,如果不存在白名单中则将其打印
            int key = StdIn.readInt();
            if (-1==rank(key, whiteList)){
                StdOut.println(key);
            };
        }
    }

    public static int rank(int key, int[] arr){
        //数组必须是有序的
        int lo = 0;
        int hi = arr.length-1;
        while(lo<=hi){
            //被查找的键要么不存在,要么必然存在于arr[lo...hi]之间
            int mid = lo + (hi-lo)/2;
            if (key> arr[mid]){
                lo=mid+1;
            }else if(key< arr[mid]){
                hi = mid-1;
            }else{
                return arr[mid];
            };
        }
        return -1;
    }
}

