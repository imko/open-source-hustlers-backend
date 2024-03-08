package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationRequestDTO {

  @Email(message = "Email must be in a correct format")
  @NotBlank(message = "Email must be provided")
  private String email;

  @NotBlank(message = "Password must be provided")
  private String password;
}
