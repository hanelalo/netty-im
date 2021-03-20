package org.hanelalo.netty.protocol.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.hanelalo.netty.protocol.Serializer;
import org.hanelalo.netty.protocol.SerializerAlgorithm;

public class JSONSerializer implements Serializer {

  private final ObjectMapper objectMapper;

  public JSONSerializer(){
    objectMapper = new ObjectMapper();
  }

  @Override
  public byte getSerializerAlgorithm() {
    return SerializerAlgorithm.JSON;
  }

  @Override
  public byte[] serialize(Object object) {
    try {
      return objectMapper.writeValueAsString(object).getBytes(StandardCharsets.UTF_8);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return new byte[]{};
  }

  @Override
  public <T> T deserialize(Class<T> clazz, byte[] bytes) {
    try {
      return objectMapper.readValue(new String(bytes), clazz);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

}
