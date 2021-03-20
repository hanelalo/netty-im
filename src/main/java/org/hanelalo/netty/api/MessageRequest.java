package org.hanelalo.netty.api;

import lombok.Data;
import lombok.ToString;
import org.hanelalo.netty.protocol.Command;
import org.hanelalo.netty.protocol.Packet;

@Data
@ToString
public class MessageRequest extends Packet {

  private String toUserId;

  private String message;

  @Override
  public byte getCommand() {
    return Command.MESSAGE_REQUEST;
  }
}
