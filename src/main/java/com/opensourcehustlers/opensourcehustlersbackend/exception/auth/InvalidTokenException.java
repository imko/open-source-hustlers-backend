package com.opensourcehustlers.opensourcehustlersbackend.exception.auth;

public class InvalidTokenException extends RuntimeException {

  public InvalidTokenException() {
    super("Invalid Bearer token");
  }
}
