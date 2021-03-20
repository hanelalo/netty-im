package org.hanelalo.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.hanelalo.netty.api.MessageResponse;

public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponse> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, MessageResponse msg) {
    System.out.println("来自 "+msg.getFromUserId()+": \n"+msg.getMessage());
  }
}
