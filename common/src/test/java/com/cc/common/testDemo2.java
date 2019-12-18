package com.cc.common;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author: Wayne
 * @Date: 2019/12/8 19:57
 * @Version: 1.0
 */
public class testDemo2 {

    public static int[] arr_100000(){
        int[] arr = new int[100000];
        for (int i = 0; i < 100000; i++) {
            int v = (int)(Math.random() * 100000);
            arr[i] = v;
        }
        return arr;
    }

    @Test
    public static void selectionSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i]> arr[j]){
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] =temp;
                }

            }
        }
    }

    @Test
    public static void bubbleSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j <= arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    @Test
    public static void quickSort(int[] arr,int low, int high){
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //temp就是基准位
        temp = arr[low];

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //如果满足条件则交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }
        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }

    @Test
    public static void mergeSort(int[] a, int l, int m, int r, int deep){
        int[] arrSort = new int[r-l+1];
        int i=l,j= m+1, k=0;
        while (i <= m&&j <= r) {
            if(a[i]<a[j]){
                arrSort[k] = a[i];
                i++;
                k++;
            } else{
                arrSort[k] = a[j];
                j++;
                k++;
            }
        }
//        if (k<a.length){
            while(i<=m){
                arrSort[k] = a[i];
                i++;
                k++;
            }
            while(j<= r){
                arrSort[k] = a[j];
                j++;
                k++;
            }
//        }
        for (int n = 0; n < arrSort.length; n++) {
            a[n+l] = arrSort[n];
        }
        deep--;
    }

    private static void merge(int[] arr, int l, int r, int deep) {
        deep+=1;
        int m = (l+r)/2;
        if(l<r){
            merge(arr,l,m,deep);
            merge(arr,m+1,r,deep);
            mergeSort(arr,l,m,r,deep);
        }
    }

    public static void main(String[] args) {
        int[] arr = arr_100000();
        int[] arr1 = {2,7,8,9,3,4,5,6};
        int[] arr2 = {3,1,2,9,56,6,4,8,12};
        long before = System.currentTimeMillis();
//        quickSort(arr,0,arr.length-1);
//        mergeSort(arr2,0,1,2,0);
        merge(arr, 0, arr.length-1,0);
        long after = System.currentTimeMillis();
        long l = (after - before);
        System.out.println(Arrays.toString(arr));
        System.out.println("耗时:" + l);
    }
}
