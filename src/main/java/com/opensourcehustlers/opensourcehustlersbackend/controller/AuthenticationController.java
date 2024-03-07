package com.opensourcehustlers.opensourcehustlersbackend.controller;

import com.opensourcehustlers.opensourcehustlersbackend.dto.LoginResponseDTO;
import com.opensourcehustlers.opensourcehustlersbackend.dto.RegistrationDTO;
import com.opensourcehustlers.opensourcehustlersbackend.model.User;
import com.opensourcehustlers.opensourcehustlersbackend.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/api/auth", produces = "application/json")
@AllArgsConstructor
@CrossOrigin("*") // TODO: Remove later.
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  public User register(@RequestBody RegistrationDTO data) {
    return authenticationService.register(data.getUsername(), data.getPassword());
  }

  @PostMapping("/login")
  public LoginResponseDTO login(@RequestBody RegistrationDTO data) {
    return authenticationService.login(data.getUsername(), data.getPassword());
  }
}
