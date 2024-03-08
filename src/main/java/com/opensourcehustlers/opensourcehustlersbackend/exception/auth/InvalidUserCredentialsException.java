package com.opensourcehustlers.opensourcehustlersbackend.exception.auth;

public class InvalidUserCredentialsException extends RuntimeException {

  public InvalidUserCredentialsException() {
    super("Invalid user credentials");
  }
}
