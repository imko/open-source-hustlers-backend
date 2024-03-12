package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LogoutRequestDTO {

  @Email(message = "Email must be in a correct format")
  @NotBlank(message = "Email must be provided")
  private String email;

  @NotNull(message = "User ID must be provided")
  private Long id;
}
