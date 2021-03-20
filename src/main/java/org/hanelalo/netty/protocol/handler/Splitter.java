package org.hanelalo.netty.protocol.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import org.hanelalo.netty.protocol.PacketCodeC;

/**
 * 自动拆包
 * 实现 LengthFieldBasedFrameDecoder，支持带数据长度标识的自定义协议的拆包
 */
public class Splitter extends LengthFieldBasedFrameDecoder {

  /**
   * @param maxFrameLength 每一个窗口最大长度
   * @param lengthFieldOffset 长度标识偏移量
   * @param lengthFieldLength 长度标识符数据长度
   */
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
