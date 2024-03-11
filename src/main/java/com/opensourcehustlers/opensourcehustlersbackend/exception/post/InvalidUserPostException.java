package com.opensourcehustlers.opensourcehustlersbackend.exception.post;

public class InvalidUserPostException extends RuntimeException {

  public InvalidUserPostException(String userEmail, Long postId) {
    super(String.format("User %s cannot perform action on post %d", userEmail, postId));
  }
}
