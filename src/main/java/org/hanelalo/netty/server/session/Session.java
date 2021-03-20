package org.hanelalo.netty.server.session;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class Session {
  private String userId;
  private String username;
}
