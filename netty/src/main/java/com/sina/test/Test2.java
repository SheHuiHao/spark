package com.sina.test;

public class Test2  {
    public static void main(String[] args) {
        Test1 t=new Test1() {
            public void sayHello(String name) {
                System.out.println("Hello"+name);
            }
        };
    }

}
