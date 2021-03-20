package org.hanelalo.netty.protocol;

import java.util.Arrays;
import org.hanelalo.netty.api.LoginRequest;
import org.hanelalo.netty.api.LoginResponse;
import org.hanelalo.netty.api.MessageRequest;
import org.hanelalo.netty.api.MessageResponse;

public enum CommandUtil {

  LOGIN_REQUEST(Command.LOGIN_REQUEST, LoginRequest.class),
  LOGIN_RESPONSE(Command.LOGIN_RESPONSE, LoginResponse.class),
  MESSAGE_REQUEST(Command.MESSAGE_REQUEST, MessageRequest.class),
  MESSAGE_RESPONSE(Command.MESSAGE_RESPONSE, MessageResponse.class);

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
