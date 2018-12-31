package com.sina.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.List;

public class ObjectEncoder extends MessageToMessageEncoder<Object> {
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, List<Object> list) throws Exception {
        System.out.println("编码中...");
        ByteBuf buffer = channelHandlerContext.alloc().buffer();
        byte[] bytes = SerializationUtils.serialize((Serializable) o);
        buffer.writeBytes(bytes);
        //将数据向下传递
        list.add(buffer);
    }
}
