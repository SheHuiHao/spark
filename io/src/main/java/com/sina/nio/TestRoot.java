package com.sina.nio;

import javafx.scene.Scene;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestRoot {
    public static void main(String[] args) {
       /* int a=1;
        int b=3;
        //在不使用第三变量的情况下   交换
        System.out.println("a"+a+"  "+"b="+b);
        a=a+b;  //  a 4
        b=a-b;   // b -2
        a=a-b;  //
        System.out.println("a"+a+"  "+"b="+b);*/

       //在一个集合中有若干数 不使用排序 找出第二大的值
        List<Integer> list= new ArrayList<Integer>();
        for(int i=1;i<=100;i++){
            list.add(i);
        }

        for (Integer secord : list) {
            int max=Collections.max(list); int max2=secord;
            if(secord>max2){
                if(secord>max){
                    max2=max;
                    max= secord;
                }else {
                    max2=secord;
                }
            }
            System.out.println("最大值"+max);
            System.out.println("第二大值"+max2);
        }

    }
}
