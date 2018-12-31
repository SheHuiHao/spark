package com.sina.nio;

import sun.java2d.pipe.AAShapePipe;

import java.util.ArrayList;

public class TestString {
    public static void main(String[] args) {
        //统计子字符串出现在字符串中的全部下标位置
       String str1="aabcdhskjfhjskfabcabcabc";
       String str2="abc";
       int a=0;
       while(true){
           int i = str1.indexOf(str2, a);
           if(i==-1) break;
            a = i + str2.length();
           System.out.println(i);
       }
        System.out.println("豪哥666");
    }
}
