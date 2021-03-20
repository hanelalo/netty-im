package org.hanelalo.netty.server.session;

import io.netty.channel.Channel;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.hanelalo.netty.Attributes;

public class SessionBinding {

  private static final Map<String, Channel> USERID_CHANNEL_MAP = new ConcurrentHashMap<>();

  public static void bindSession(Channel channel, Session session){
    USERID_CHANNEL_MAP.putIfAbsent(session.getUserId(), channel);
    channel.attr(Attributes.SESSION).set(session);
  }

  public static void unbindSession(Channel channel){
    USERID_CHANNEL_MAP.remove(channel.attr(Attributes.SESSION).get().getUserId());
  }

  public static boolean isLogin(Channel channel){
    return Objects.nonNull(channel.attr(Attributes.SESSION));
  }

  public static Session getSession(Channel channel){
    return channel.attr(Attributes.SESSION).get();
  }

  public static Channel getChannel(String userId){
    return USERID_CHANNEL_MAP.get(userId);
  }

}
