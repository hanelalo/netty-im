package org.hanelalo.netty.protocol.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import java.util.List;
import org.hanelalo.netty.protocol.Packet;
import org.hanelalo.netty.protocol.PacketCodeC;

/**
 * 继承网络数据的编码和解码
 */
@Sharable
public class PacketCodeCHandler extends MessageToMessageCodec<ByteBuf, Packet> {

  public static PacketCodeCHandler INSTANCE = new PacketCodeCHandler();

  @Override
  protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
    ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
    PacketCodeC.INSTANCE.encode(byteBuf, msg);
    out.add(byteBuf);
  }

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
    out.add(PacketCodeC.INSTANCE.decode(msg));
  }
}
