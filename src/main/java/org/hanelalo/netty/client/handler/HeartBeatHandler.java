package org.hanelalo.netty.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.concurrent.TimeUnit;
import org.hanelalo.netty.api.HeartBeatRequest;

public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

  private static final long HEARTBEAT_INTERVAL = 5;

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    scheduleSendHeadBeat(ctx);
    super.channelActive(ctx);
  }

  private void scheduleSendHeadBeat(ChannelHandlerContext ctx) {
    ctx.executor()
        .schedule(
            () -> {
              if (ctx.channel().isActive()) {
                ctx.channel().writeAndFlush(new HeartBeatRequest());
                scheduleSendHeadBeat(ctx);
              }
            },
            HEARTBEAT_INTERVAL,
            TimeUnit.SECONDS);
  }
}
