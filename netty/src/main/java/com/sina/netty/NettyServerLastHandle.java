package com.sina.netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

import java.util.Date;

public class NettyServerLastHandle extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("服务器收到了"+msg);
        //可变长的字节缓冲区
        ChannelFuture channelFuture = ctx.writeAndFlush(new Date());

        //关闭通信
        channelFuture.addListener(ChannelFutureListener.CLOSE);
        channelFuture.addListener(ChannelFutureListener.FIRE_EXCEPTION_ON_FAILURE);
        channelFuture.addListener(ChannelFutureListener.CLOSE_ON_FAILURE);

    }
}
