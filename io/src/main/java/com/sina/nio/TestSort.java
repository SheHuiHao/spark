package com.sina.nio;

public class TestSort {
    public static void main(String[] args) {
        int[] a={1,4,3,2,5,6,324,34,32423,32432};
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length-i-1;j++){
                if(a[j]>a[j+1]){
                    int t=a[j];
                    a[j]=a[j+1];
                    a[j+1]=t;
                }
            }
        }
        for(int aa=0;aa<a.length;aa++){
            System.out.println(a[aa]);
        }
    }
}
