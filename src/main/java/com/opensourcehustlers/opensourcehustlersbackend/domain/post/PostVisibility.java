package com.opensourcehustlers.opensourcehustlersbackend.domain.post;

import java.util.HashMap;
import java.util.Map;

public enum PostVisibility {
  PUBLIC("Public"),
  PRIVATE("Private");

  public final String name;
  private static final Map<String, PostVisibility> BY_NAME = new HashMap<>();

  static {
    for (PostVisibility postVisibility : values()) {
      BY_NAME.put(postVisibility.name, postVisibility);
    }
  }

  private PostVisibility(String name) {
    this.name = name;
  }

  public static PostVisibility valueOfName(String name) {
    return BY_NAME.get(name);
  }
}
