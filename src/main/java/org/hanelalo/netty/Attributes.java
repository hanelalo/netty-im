package org.hanelalo.netty;

import io.netty.util.AttributeKey;
import org.hanelalo.netty.server.session.Session;

public interface Attributes {

  AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

  AttributeKey<Session> SESSION = AttributeKey.newInstance("SESSION");
}
