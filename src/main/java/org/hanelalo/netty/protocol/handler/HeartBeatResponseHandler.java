package org.hanelalo.netty.protocol.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.hanelalo.netty.api.HeartBeatResponse;

public class HeartBeatResponseHandler extends SimpleChannelInboundHandler<HeartBeatResponse> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, HeartBeatResponse msg) throws Exception {
    System.out.println(msg);
  }
}
