package com.sina.test;

import org.apache.parquet.io.ValidatingRecordConsumer;

import java.io.*;

public class TestIO {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("D:\\oo.jpg");
        FileOutputStream fos = new FileOutputStream("D:\\JB.jpg");
        byte[] bytes = new byte[1024];
        while (true){
            int read = fis.read(bytes);
            if(read==-1) break;
            fos.write(bytes,0,read);
        }
        fos.close();
        fis.close();
    }
}
