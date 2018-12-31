package com.sina.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //获取服务端通道管道
        ChannelPipeline pipeline = socketChannel.pipeline();

        //挂载编码器 必须保证在末端的 前面
        pipeline.addLast(new ObjectDecoder());
        //添加最末端的处理(通道末端 用来收发数据)
        pipeline.addLast(new NettyServerLastHandle());
    }
}
