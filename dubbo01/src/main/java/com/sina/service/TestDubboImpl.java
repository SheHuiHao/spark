package com.sina.service;

public class TestDubboImpl implements TestDubbo {
    public int sum(int a, int b) {
        return a-b;
    }

    public int product(int c, int b) {
        return c*b;
    }
}
