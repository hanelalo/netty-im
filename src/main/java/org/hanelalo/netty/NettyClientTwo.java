package org.hanelalo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Scanner;
import org.hanelalo.netty.api.MessageRequest;
import org.hanelalo.netty.client.handler.LoginResponseHandler;
import org.hanelalo.netty.client.handler.MessageResponseHandler;
import org.hanelalo.netty.protocol.PacketDecoder;
import org.hanelalo.netty.protocol.PacketEncoder;
import org.hanelalo.netty.protocol.Splitter;
import org.hanelalo.netty.client.LoginUtils;

public class NettyClientTwo {

  public static void main(String[] args) {
    Bootstrap bootstrap = new Bootstrap();
    NioEventLoopGroup group = new NioEventLoopGroup();
    bootstrap
        .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
        .option(ChannelOption.SO_KEEPALIVE, true)
        .option(ChannelOption.TCP_NODELAY, true)
        .attr(Attributes.LOGIN, false)
        .group(group)
        .channel(NioSocketChannel.class)
        .handler(
            new ChannelInitializer<NioSocketChannel>() {
              @Override
              protected void initChannel(NioSocketChannel ch) throws Exception {
                // 自动拆包
                ch.pipeline().addLast(new Splitter(Integer.MAX_VALUE, 7, 4));
                //                ch.pipeline().addLast(new FirstClientHandler());
                ch.pipeline().addLast(new PacketDecoder());
                ch.pipeline().addLast(new LoginResponseHandler());
                ch.pipeline().addLast(new MessageResponseHandler());
                ch.pipeline().addLast(new PacketEncoder());
              }
            })
        .connect("127.0.0.1", 8000)
        .addListener(
            future -> {
              if (future.isSuccess()) {
                Channel channel = ((ChannelFuture) future).channel();
                startConsoleThread(channel);
              }
            });
  }

  private static void startConsoleThread(Channel channel) {
    new Thread(
            () -> {
              while (!Thread.interrupted()) {
                if (LoginUtils.isLogin(channel)) {
                  System.out.println("目标的 userId:");
                  Scanner sc = new Scanner(System.in);
                  MessageRequest messageRequest = new MessageRequest();
                  String userId = sc.nextLine();
                  messageRequest.setToUserId(userId);
                  System.out.println("输入消息:");
                  String line = sc.nextLine();
                  messageRequest.setMessage(line);
                  channel.writeAndFlush(messageRequest);
                }
              }
            })
        .start();
  }
}
