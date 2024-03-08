package com.opensourcehustlers.opensourcehustlersbackend.exception.auth;

public class UserAlreadyExistsException extends RuntimeException {

  public UserAlreadyExistsException(String email) {
    super(String.format("User already exists: %s", email));
  }
}
