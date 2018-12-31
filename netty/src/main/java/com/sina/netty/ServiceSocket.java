package com.sina.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServiceSocket {
    public static void main(String[] args) throws InterruptedException {
        //1.创建服务启动引导
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //2.创建boss/worker线程池
        NioEventLoopGroup boss = new NioEventLoopGroup();
        NioEventLoopGroup worker = new NioEventLoopGroup();
        //3.设置线程池组
        serverBootstrap.group(boss,worker);
        //4.设置服务器端实现类
        serverBootstrap.channel(NioServerSocketChannel.class);
        //5.初始化通信管道
        serverBootstrap.childHandler(new ServerChannelInitializer());
        //6.启动服务器
        System.out.println("开始服务...");
        ChannelFuture future=serverBootstrap.bind(9999).sync();
        //7.关闭服务通道
        future.channel().closeFuture().sync();
        //8.释放线程资源
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }
}
