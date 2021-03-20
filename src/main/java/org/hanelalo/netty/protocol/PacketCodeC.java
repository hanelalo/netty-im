package org.hanelalo.netty.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class PacketCodeC {

  public static final int MAGIC_NUMBER = 0x12345678;

  public static PacketCodeC INSTANCE = new PacketCodeC();

  private PacketCodeC(){}

  @Deprecated
  public ByteBuf encode(Packet packet){
    ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
    byte[] bytes = Serializer.DEFAULT.serialize(packet);

    // 魔数，用来忽悠 http
    byteBuf.writeInt(MAGIC_NUMBER);
    // 版本号
    byteBuf.writeByte(packet.getVersion());
    // 序列化算法
    byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
    // 命令类型
    byteBuf.writeByte(packet.getCommand());
    // 请求数据主体长度
    byteBuf.writeInt(bytes.length);
    // 请求主体数据
    byteBuf.writeBytes(bytes);

    return byteBuf;
  }

  public Packet decode(ByteBuf byteBuf){
    // 跳过魔数
    byteBuf.skipBytes(4);
    // 跳过版本号
    byteBuf.skipBytes(1);
    // 序列化算法
    byte algorithm = byteBuf.readByte();
    // 命令类型
    byte command = byteBuf.readByte();
    // 数据长度
    int packageLength = byteBuf.readInt();
    // 请求数据
    byte[] dataBytes = new byte[packageLength];
    byteBuf.readBytes(dataBytes);
    // 根据命令类型获取请求类型
    Class<? extends Packet> clazzType = getClazzTypeBy(command);
    return Serializer.DEFAULT.deserialize(clazzType, dataBytes);
  }

  private Class<? extends Packet> getClazzTypeBy(byte command) {
    return CommandUtil.fromCommand(command);
  }

  public void encode(ByteBuf byteBuf, Packet packet) {
    byte[] bytes = Serializer.DEFAULT.serialize(packet);
    // 魔数，用来忽悠 http
    byteBuf.writeInt(MAGIC_NUMBER);
    // 版本号
    byteBuf.writeByte(packet.getVersion());
    // 序列化算法
    byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlgorithm());
    // 命令类型
    byteBuf.writeByte(packet.getCommand());
    // 请求数据主体长度
    byteBuf.writeInt(bytes.length);
    // 请求主体数据
    byteBuf.writeBytes(bytes);
  }
}
