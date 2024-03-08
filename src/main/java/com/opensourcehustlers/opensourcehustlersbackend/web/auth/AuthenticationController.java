package com.opensourcehustlers.opensourcehustlersbackend.web.auth;

import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.AuthenticationRequestDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.AuthenticationResponseDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.AuthenticationService;
import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.RegistrationRequestDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.user.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping(path = "/v1/api/auth", produces = "application/json")
@RestController
public class AuthenticationController {

  private final AuthenticationService authenticationService;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegistrationRequestDTO data) {
    return ResponseEntity.ok(authenticationService.register(data));
  }

  @PostMapping("/login")
  public ResponseEntity<AuthenticationResponseDTO> login(
      @Valid @RequestBody AuthenticationRequestDTO data) {
    return ResponseEntity.ok(authenticationService.login(data));
  }
}
