package org.hanelalo.netty.protocol;

import java.util.Arrays;
import org.hanelalo.netty.api.HeartBeatRequest;
import org.hanelalo.netty.api.HeartBeatResponse;
import org.hanelalo.netty.api.LoginRequest;
import org.hanelalo.netty.api.LoginResponse;
import org.hanelalo.netty.api.MessageRequest;
import org.hanelalo.netty.api.MessageResponse;
import org.hanelalo.netty.server.handler.HeartBeatRequestHandler;

public enum CommandUtil {

  LOGIN_REQUEST(Command.LOGIN_REQUEST, LoginRequest.class),
  LOGIN_RESPONSE(Command.LOGIN_RESPONSE, LoginResponse.class),
  MESSAGE_REQUEST(Command.MESSAGE_REQUEST, MessageRequest.class),
  MESSAGE_RESPONSE(Command.MESSAGE_RESPONSE, MessageResponse.class),
  HEARTBEAT_RESPONSE(Command.HEART_BEAT_RESPONSE, HeartBeatResponse.class),
  HEARTBEAT_REQUEST(Command.HEART_BEAT_REQUEST, HeartBeatRequest.class);

  private final byte command;
  private final Class clazz;

  CommandUtil(byte command, Class<? extends Packet> clazz) {
    this.command = command;
    this.clazz = clazz;
  }

  public static Class<? extends Packet> fromCommand(byte command){
    return Arrays.stream(CommandUtil.values())
        .filter(value -> value.command == command).map(value -> value.clazz)
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(String.valueOf(command)));
  }

}
