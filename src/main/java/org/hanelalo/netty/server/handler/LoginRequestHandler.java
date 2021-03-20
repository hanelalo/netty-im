package org.hanelalo.netty.server.handler;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.UUID;
import org.hanelalo.netty.Attributes;
import org.hanelalo.netty.api.LoginRequest;
import org.hanelalo.netty.api.LoginResponse;
import org.hanelalo.netty.server.session.Session;
import org.hanelalo.netty.server.session.SessionBinding;

/**
 * 登录请求处理器，实现 SimpleChannelInboundHandler，支持对特定类型的处理
 */
@Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequest> {

  public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, LoginRequest loginRequest) {
    ctx.channel().attr(Attributes.LOGIN).set(true);
    ctx.channel().writeAndFlush(handleLoginRequest(ctx,loginRequest));
  }

  private LoginResponse handleLoginRequest(ChannelHandlerContext ctx,
      LoginRequest loginRequest) {
    System.out.println(loginRequest);
    LoginResponse loginResponse = new LoginResponse();
    String userId = UUID.randomUUID().toString().substring(0, 5);
    SessionBinding.bindSession(ctx.channel(),
        Session.builder().userId(userId).username(loginRequest.getUsername()).build());
    loginResponse.setMsg("login success, your userId: "+userId);
    loginResponse.setSuccess(true);
    return loginResponse;
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    SessionBinding.unbindSession(ctx.channel());
    super.channelInactive(ctx);
  }
}
