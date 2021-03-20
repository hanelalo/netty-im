package org.hanelalo.netty.server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.hanelalo.netty.api.MessageRequest;
import org.hanelalo.netty.api.MessageResponse;
import org.hanelalo.netty.server.session.SessionBinding;

public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequest> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, MessageRequest msg) throws Exception {
    handleResponse(ctx,msg);
  }

  private void handleResponse(ChannelHandlerContext ctx, MessageRequest msg) {
    String toUserId = msg.getToUserId();
    Channel channel = SessionBinding.getChannel(toUserId);
    MessageResponse response = new MessageResponse();
    response.setMessage(msg.getMessage());
    response.setFromUserId(SessionBinding.getSession(ctx.channel()).getUserId());
    channel.writeAndFlush(response);
  }
}
