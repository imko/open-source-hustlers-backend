package com.opensourcehustlers.opensourcehustlersbackend.web.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/v1/api/users", produces = "application/json")
@RestController
public class UserController {
  @GetMapping
  public String helloUser() {
    return "Hello, this is user access level!";
  }
}
