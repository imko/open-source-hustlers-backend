package com.opensourcehustlers.opensourcehustlersbackend.web.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/api/admin", produces = "application/json")
@CrossOrigin("*") // TODO: Remove later.
public class AdminController {

  @GetMapping
  public String helloAdmin() {
    return "Hello, this is admin level access!";
  }
}
