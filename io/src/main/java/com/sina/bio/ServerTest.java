package com.sina.bio;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class ServerTest {
    public static void main(String[] args) throws Exception {
        //socket 指的是客户端与服务端的对话


        //创建ServiceSockte 用于转发
        ServerSocket serverSocket = new ServerSocket();
        //指定端口  不指定主机 默认是0000
        serverSocket.bind(new InetSocketAddress(9998));
        //转发  在BIO中是阻塞的
        // 是先创建线程 搁这等着
        // 等待着IO操作  啥时候来啥时候将IO操作进行转发
        //这样会使线程得不到充分的利用
        //垃圾 BIO
        //返回值就是它转发的IO操作
        System.out.println("正在阻塞");
        Socket socket = serverSocket.accept();
        //读取客户端请求
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder stringBuilder = new StringBuilder();
        while (true){
            String s = bufferedReader.readLine();
            if(s==null) break;
            stringBuilder.append(s.getBytes());
        }
        //响应客户端
        OutputStream os = socket.getOutputStream();
        os.write("来自服务器的响应".getBytes());
        os.flush();
        //告知客户端 写结束
        socket.shutdownOutput();
        //关闭socket
        socket.close();

    }
}
