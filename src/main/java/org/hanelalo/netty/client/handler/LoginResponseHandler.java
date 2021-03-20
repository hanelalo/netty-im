package org.hanelalo.netty.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.hanelalo.netty.Attributes;
import org.hanelalo.netty.api.LoginRequest;
import org.hanelalo.netty.api.LoginResponse;
import org.hanelalo.netty.protocol.PacketCodeC;

public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponse> {

  @Override
  public void channelActive(ChannelHandlerContext ctx) {
    ByteBuf byteBuffer = getByteBuffer();
    ctx.channel().writeAndFlush(byteBuffer);
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginResponse response) throws Exception {
    System.out.println(response.getMsg());
    if(response.isSuccess()){
      ctx.channel().attr(Attributes.LOGIN).set(true);
    }
  }

  private ByteBuf getByteBuffer() {
    LoginRequest packet = new LoginRequest();
    packet.setUsername("hanelalo");
    packet.setPassword("123456");
    return PacketCodeC.INSTANCE.encode(packet);
  }

}
