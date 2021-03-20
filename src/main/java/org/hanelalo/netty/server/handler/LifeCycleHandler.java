package org.hanelalo.netty.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * handler 生命周期管理
 */
public class LifeCycleHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
    System.out.println("添加了处理器...");
    super.handlerAdded(ctx);
  }

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("管道注册成功...");
    super.channelRegistered(ctx);
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("连接就绪...");
    super.channelActive(ctx);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("有新数据来了...");
    super.channelRead(ctx, msg);
  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    System.out.println("本次数据读取完毕...");
    super.channelReadComplete(ctx);
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("管道关闭...");
    super.channelInactive(ctx);
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("管道注销成功...");
    super.channelUnregistered(ctx);
  }

  @Override
  public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
    System.out.println("删除处理器...");
    super.handlerRemoved(ctx);
  }
}
