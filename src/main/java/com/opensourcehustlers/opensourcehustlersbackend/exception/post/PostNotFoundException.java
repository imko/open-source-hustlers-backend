package com.opensourcehustlers.opensourcehustlersbackend.exception.post;

public class PostNotFoundException extends RuntimeException {

  public PostNotFoundException(Long id) {
    super(String.format("Post not found: %d", id));
  }
}
