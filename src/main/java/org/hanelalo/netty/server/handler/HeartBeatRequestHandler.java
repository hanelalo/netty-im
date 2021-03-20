package org.hanelalo.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.hanelalo.netty.api.HeartBeatRequest;
import org.hanelalo.netty.api.HeartBeatResponse;

public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequest> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequest msg) {
    System.out.println(msg);
    ctx.writeAndFlush(new HeartBeatResponse());
  }
}
