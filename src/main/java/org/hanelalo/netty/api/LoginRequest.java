package org.hanelalo.netty.api;

import static org.hanelalo.netty.protocol.Command.LOGIN_REQUEST;

import lombok.Data;
import lombok.ToString;
import org.hanelalo.netty.protocol.Packet;

@Data
@ToString(callSuper = true)
public class LoginRequest extends Packet {

  private String username;
  private String password;

  @Override
  public byte getCommand() {
    return LOGIN_REQUEST;
  }
}
