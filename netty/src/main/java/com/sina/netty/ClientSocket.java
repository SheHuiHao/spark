package com.sina.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientSocket {
    public static void main(String[] args) throws InterruptedException {
        //1.创建服务启动引导
        Bootstrap bt = new Bootstrap();
        //2.创建worker线程池
        NioEventLoopGroup worker = new NioEventLoopGroup();
        //3.设置线程池组
        bt.group(worker);
        //4.设置客户端实现类
        bt.channel(NioSocketChannel.class);
        //5.初始化通信管道
        bt.handler(new ClientChannelInitializer());
        //6.启动服务器
        ChannelFuture future = bt.connect("127.0.0.1", 9999).sync();
        //7.关闭服务通道
        future.channel().closeFuture().sync();
        //8.释放线程资源
        worker.shutdownGracefully();
    }
}
