package org.hanelalo.netty.client;

import io.netty.channel.Channel;
import java.util.Scanner;
import org.hanelalo.netty.api.MessageRequest;

public class MessageThread extends Thread{

  public MessageThread(Channel channel) {
    super(() -> {
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
    });
  }
}
