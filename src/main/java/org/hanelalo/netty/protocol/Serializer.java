package org.hanelalo.netty.protocol;

import org.hanelalo.netty.protocol.json.JSONSerializer;

/**
 * 序列化
 */
public interface Serializer {

  /**
   * json 序列化
   */
  byte JSON_SERIALIZER = 1;

  Serializer DEFAULT = new JSONSerializer();

  /**
   * 序列化算法
   * @return byte
   */
  byte getSerializerAlgorithm();

  /**
   * 序列化
   */
  byte[] serialize(Object object);

  /**
   * 反序列化
   */
  <T> T deserialize(Class<T> clazz, byte[] bytes);
}
