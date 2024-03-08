package com.opensourcehustlers.opensourcehustlersbackend.exception.auth;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String email) {
    super(String.format("User not found: %s", email));
  }
}
