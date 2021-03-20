package org.hanelalo.netty.client;

import io.netty.channel.Channel;
import org.hanelalo.netty.Attributes;

public class LoginUtils {

  public static boolean isLogin(Channel channel){
    return channel.attr(Attributes.LOGIN).get();
  }

}
