package com.sina.bio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RootTest {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress("127.0.0.1",9998));
        //给服务器发消息
        OutputStream os = socket.getOutputStream();
        os.write("我是服务端".getBytes());
        os.flush();
        //接收服务器的响应
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder stringBuilder = new StringBuilder();
        while (true){
            String s = bufferedReader.readLine();
            if(s==null) break;
            stringBuilder.append(s);
        }
        System.out.println("客户端的消息"+ stringBuilder.toString());
        socket.shutdownInput();
        socket.close();
    }
}
