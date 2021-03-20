package org.hanelalo.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 自动拆包
 */
public class Splitter extends LengthFieldBasedFrameDecoder {

  public Splitter(int maxFrameLength, int lengthFieldOffset,
      int lengthFieldLength) {
    super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
  }

  @Override
  protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
    // 非本协议连接，拒绝
    if(in.getInt(in.readerIndex()) != PacketCodeC.MAGIC_NUMBER){
      System.out.println("非本协议连接...");
      ctx.channel().close();
      return null;
    }
    return super.decode(ctx, in);
  }
}
