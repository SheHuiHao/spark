package com.sina.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {


    protected void initChannel(SocketChannel socketChannel) throws Exception {
            //获取客户端通道
        ChannelPipeline pipeline = socketChannel.pipeline();


        //挂载客户端编码器 必须在最末端前 因为它是对就收来的数据做解码
        pipeline.addLast(new ObjectEncoder());
        //添加最末端的处理(管道的最末端是用来收发的)
        pipeline.addLast(new NettyClientLastHandle());
    }
}
