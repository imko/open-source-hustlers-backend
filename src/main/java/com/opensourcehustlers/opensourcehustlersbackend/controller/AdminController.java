package com.opensourcehustlers.opensourcehustlersbackend.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/admin")
@CrossOrigin("*") // TODO: Remove later.
public class AdminController {

  @GetMapping
  public String helloAdmin() {
    return "Hello, this is admin level access!";
  }
}
