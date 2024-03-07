package com.opensourcehustlers.opensourcehustlersbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/api/users", produces = "application/json")
@CrossOrigin("*") // TODO: Remove later.
public class UserController {

  @GetMapping
  public String helloUser() {
    return "Hello, this is user access level!";
  }
}
