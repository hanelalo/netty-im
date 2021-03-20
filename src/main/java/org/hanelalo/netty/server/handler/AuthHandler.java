package org.hanelalo.netty.server.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.hanelalo.netty.server.session.SessionBinding;

/**
 * 登录验证
 */
@Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {

  public static final AuthHandler INSTANCE = new AuthHandler();

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    System.out.println("添加处理器: AuthHandler...");
    super.handlerAdded(ctx);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (SessionBinding.isLogin(ctx.channel())) {
      System.out.println("当前用户已登录");
      ctx.pipeline().remove(this);
      super.channelRead(ctx, msg);
    }else{
      ctx.channel().close();
    }
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    System.out.println("删除处理器: AuthHandler...");
    super.handlerRemoved(ctx);
  }
}
