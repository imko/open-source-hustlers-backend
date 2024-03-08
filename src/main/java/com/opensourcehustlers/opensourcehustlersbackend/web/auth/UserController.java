package com.opensourcehustlers.opensourcehustlersbackend.web.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/v1/api/users", produces = "application/json")
@RestController
public class UserController {
  // TODO: Remove this controller after testing role based authorization.

  @GetMapping
  public String helloUser() {
    return "Hello, this is user access level!";
  }
}
