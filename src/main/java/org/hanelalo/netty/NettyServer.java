package org.hanelalo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.hanelalo.netty.protocol.handler.PacketCodeCHandler;
import org.hanelalo.netty.protocol.handler.Splitter;
import org.hanelalo.netty.server.handler.AuthHandler;
import org.hanelalo.netty.server.handler.ClientIdleStateHandler;
import org.hanelalo.netty.server.handler.HeartBeatRequestHandler;
import org.hanelalo.netty.server.handler.LoginRequestHandler;
import org.hanelalo.netty.server.handler.MessageRequestHandler;

public class NettyServer {
  public static void main(String[] args) {

    ServerBootstrap serverBootstrap = new ServerBootstrap();
    /** handle connection event */
    NioEventLoopGroup boss = new NioEventLoopGroup();
    /** handle io event, business operation */
    NioEventLoopGroup worker = new NioEventLoopGroup();
    serverBootstrap
        .group(boss, worker)
        // switch io model, general Nio, you can use OIO, but why use netty ?
        .channel(NioServerSocketChannel.class)
        .attr(Attributes.LOGIN, false)
        .childHandler(
            new ChannelInitializer<NioSocketChannel>() {
              @Override
              protected void initChannel(NioSocketChannel ch) {
                // define every request handle logic, mainly business
                // 空闲超时断开连接
                ch.pipeline().addLast(new ClientIdleStateHandler(10, 10, 10));
                ch.pipeline().addLast(new Splitter(Integer.MAX_VALUE, 7, 4));
                ch.pipeline().addLast(PacketCodeCHandler.INSTANCE);
                ch.pipeline().addLast(LoginRequestHandler.INSTANCE);
                ch.pipeline().addLast(AuthHandler.INSTANCE);
                ch.pipeline().addLast(new HeartBeatRequestHandler());
                ch.pipeline().addLast(MessageRequestHandler.INSTANCE);
              }
            });
    ChannelFuture bind = serverBootstrap.bind(8000);
    bind.addListener(
        future -> {
          if (future.isSuccess()) {
            System.out.println("bind port sucess");
          } else {
            System.out.println("bind port failed");
          }
        });
  }
}
