package org.hanelalo.netty.protocol;

import lombok.Data;

@Data
public abstract class Packet {

  /** 协议版本 */
  private byte version = 1;
  private String command;

  /**
   * 获取命令
   */
  public abstract byte getCommand();
}
