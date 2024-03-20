package com.opensourcehustlers.opensourcehustlersbackend.web.user;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Hidden
@RequestMapping(path = "/v1/api/admin", produces = "application/json")
@RestController
public class AdminController {
  // TODO: Remove this controller after testing role based authorization.

  @GetMapping
  public String helloAdmin() {
    return "Hello, this is admin level access!";
  }
}
