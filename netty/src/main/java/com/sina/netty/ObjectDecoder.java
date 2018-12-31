package com.sina.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.sctp.SctpMessage;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.sctp.SctpMessageToMessageDecoder;
import org.apache.commons.lang3.SerializationUtils;

import java.util.List;

public class ObjectDecoder extends MessageToMessageDecoder<ByteBuf> {


    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("解码中...");
        //构建一个byte数组
        byte[] bytes = new byte[byteBuf.readableBytes()];
        //将数据放置在bytes中
        byteBuf.readBytes(bytes);
        Object o = SerializationUtils.deserialize(bytes);
        list.add(o);
    }
}
