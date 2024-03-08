package com.opensourcehustlers.opensourcehustlersbackend.exception.auth;

public class InvalidUserAuthorityException extends RuntimeException {

  public InvalidUserAuthorityException(String authority) {
    super(String.format("Invalid user authority: %s", authority));
  }
}
