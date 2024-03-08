package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponseDTO {

  @JsonProperty("user")
  private UserResponseDTO userResponseDTO;

  @NotBlank(message = "Access token must be provided")
  @JsonProperty("access_token")
  private String accessToken;

  @NotBlank(message = "Refresh token must be provided")
  @JsonProperty("refresh_token")
  private String refreshToken;
}
