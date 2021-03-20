package org.hanelalo.netty.api;

import static org.hanelalo.netty.protocol.Command.LOGIN_RESPONSE;

import lombok.Data;
import lombok.ToString;
import org.hanelalo.netty.protocol.Packet;

@Data
@ToString(callSuper = true)
public class LoginResponse extends Packet {
  /** 登录是否成功 */
  private boolean success;
  /** 返回提示信息 */
  private String msg;

  @Override
  public byte getCommand() {
    return LOGIN_RESPONSE;
  }
}
