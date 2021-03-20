package org.hanelalo.netty.protocol;

public interface Command {

  byte LOGIN_REQUEST = 1;
  byte LOGIN_RESPONSE = 2;
  byte MESSAGE_REQUEST = 3;
  byte MESSAGE_RESPONSE = 4;
  byte HEART_BEAT_REQUEST = 5;
  byte HEART_BEAT_RESPONSE = 6;
}
