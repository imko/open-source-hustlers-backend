package com.opensourcehustlers.opensourcehustlersbackend.web.auth;

import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.AuthenticationRequestDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.AuthenticationResponseDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.AuthenticationService;
import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.LogoutRequestDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.RegistrationRequestDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.Token;
import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.TokenRequestDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.TokenService;
import com.opensourcehustlers.opensourcehustlersbackend.domain.user.UserResponseDTO;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
  private final TokenService tokenService;

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

  @PostMapping("/logout")
  public void logout(@Valid @RequestBody LogoutRequestDTO data) {
    authenticationService.logout(data);
  }

  @PostMapping("/tokens/refresh")
  public ResponseEntity<AuthenticationResponseDTO> refreshToken(
      @Valid @RequestBody TokenRequestDTO data) {
    return ResponseEntity.ok(authenticationService.refreshToken(data));
  }

  // TODO: Remove this after testing.
  @GetMapping("/tokens")
  public ResponseEntity<List<Token>> findAll() {
    return ResponseEntity.ok(tokenService.findAll());
  }
}
