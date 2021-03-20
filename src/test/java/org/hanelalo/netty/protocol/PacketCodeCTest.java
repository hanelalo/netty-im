package org.hanelalo.netty.protocol;

import static org.junit.Assert.assertEquals;

import io.netty.buffer.ByteBuf;
import org.hanelalo.netty.api.LoginRequest;
import org.junit.Test;

public class PacketCodeCTest {


  @Test
  public void withEncodeAndDecode(){
    LoginRequest loginRequest = new LoginRequest();
    loginRequest.setPassword("123456");
    loginRequest.setUsername("hanelalo");
    ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(loginRequest);
    Packet packet = PacketCodeC.INSTANCE.decode(byteBuf);
    assertEquals(packet, loginRequest);
  }



}