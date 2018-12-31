package com.sina.xmldecond;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Test {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String url="dubbo%3A%2F%2F192.168.155.1%3A20880%2Fcom.sina.service.TestDubbo%3Fanyhost%3Dtrue%26application%3DFirstDemo%26bean.name%3Dcom.sina.service.TestDubbo%26dubbo%3D2.0.2%26generic%3Dfalse%26interface%3Dcom.sina.service.TestDubbo%26methods%3Dproduct%2Csum%26pid%3D5024%26side%3Dprovider%26timestamp%3D1542445132878";
        String consumer="consumer%3A%2F%2F192.168.155.1%2Fcom.sina.service.TestDubbo%3Fapplication%3DTestDubbo_consumer%26category%3Dconsumers%26check%3Dfalse%26dubbo%3D2.0.2%26interface%3Dcom.sina.service.TestDubbo%26methods%3Dproduct%2Csum%26pid%3D4524%26side%3Dconsumer%26timestamp%3D1542451290653";
        String decodeurl = URLDecoder.decode(consumer, "utf-8");
        System.out.println(decodeurl);
    }
}
