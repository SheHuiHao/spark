package com.sina.nio;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class TestService {
    public static void main(String[] args) throws IOException {
        //创建ServerSocket  转发
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定主机和端口
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //设置其通道为非阻塞
        serverSocketChannel.configureBlocking(false);
        //创建selector通道选择器
        Selector selector =  Selector.open();
        //在selector上 注册转发的通道类型
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //通道的事件有多个
        while (true){
            //这个方法显示转发通道的事件处理列表中的selectorkey 还有多少
            System.out.println("正在选择中");
            int keys = selector.select();
            if(keys>0){
                Iterator<SelectionKey> selectionKeyIterator = selector.selectedKeys().iterator();
                //迭代处理key
                while(selectionKeyIterator.hasNext()){
                    SelectionKey Key = selectionKeyIterator.next();
                    if(Key.isAcceptable()){ //判断这个事件(key)是否是转发类型
                        System.out.println("正在转发和注册读时间");
                        //实际上返回的就是这个key所代表的SelectableChannel ServerSocketChannel是它的子类
                        ServerSocketChannel serversocket = (ServerSocketChannel) Key.channel();
                        SocketChannel socketChannel = serversocket.accept();  //非阻塞转发
                        socketChannel.configureBlocking(false); //设置io的通道为非阻塞
                        //在selector上注册通道 类型为读
                         socketChannel.register(selector, SelectionKey.OP_READ);
                    }else if (Key.isReadable()){ //如果这个key的类型为read事件类型
                        //强转
                       SocketChannel socketChannel= (SocketChannel) Key.channel();
                       //创建bytebuffer 大小为1024个字节
                       ByteBuffer buffer = ByteBuffer.allocate(1024);
                       //创建一个输出 直接输出到内存 没有地址
                        ByteArrayOutputStream byteArrayOutputStream = null;
                        //如果附件等于空 就创建一个新的内存输出
                        if(Key.attachment()==null){
                            byteArrayOutputStream = new ByteArrayOutputStream();
                        }else {
                            //不等空 就把当前的附件赋值到内存中
                           byteArrayOutputStream= (ByteArrayOutputStream) Key.attachment();
                        }
                        //客户端传来的数据的数量
                        int readnum = socketChannel.read(buffer);
                        //如果readnum!=0 说明有数据
                        if(readnum!=0){
                            byteArrayOutputStream.write(buffer.array(),0,readnum);
                        }else{  //说明已经读完了客户端的数据
                            //设置通道的非阻塞可写可不写 因为他们属于同一个事件
                            //将写类型的事件注册到selector
                            socketChannel.register(selector,SelectionKey.OP_WRITE,byteArrayOutputStream);
                        }
                    }else if (Key.isWritable()){//如果key是写类型
                              SocketChannel writesockte= (SocketChannel) Key.channel();
                              //获得key所对应的附件
                        ByteArrayOutputStream attachment = (ByteArrayOutputStream) Key.attachment();
                          //创建一个bytebuffer
                        ByteBuffer byteBuffer = ByteBuffer.wrap(attachment.toByteArray());
                        writesockte.write(byteBuffer);
                        //告知客户端写结束
                        writesockte.shutdownOutput();
                        //关闭通道
                        writesockte.close();
                    }
                }
                //完事之后 删除当前事件列表中的key (并不代表着删除通道列表中的通道)
                selectionKeyIterator.remove();
            }
        }
    }
}
