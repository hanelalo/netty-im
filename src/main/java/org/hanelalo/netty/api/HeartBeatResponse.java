package org.hanelalo.netty.api;

import org.hanelalo.netty.protocol.Command;
import org.hanelalo.netty.protocol.Packet;

public class HeartBeatResponse extends Packet {

  @Override
  public byte getCommand() {
    return Command.HEART_BEAT_RESPONSE;
  }

}
