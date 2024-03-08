package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponseDTO {

  @NotBlank(message = "User ID must be provided")
  @JsonProperty("user_id")
  private Long userId;

  @NotBlank(message = "Display name must be provided")
  @JsonProperty("display_name")
  private String displayName;

  @Email(message = "Email must be in a correct format")
  @NotBlank(message = "Email must be provided")
  private String email;

  @NotNull(message = "Enabled must be provided")
  private Boolean enabled;

  @NotNull(message = "User role must be provided")
  @JsonProperty("user_role")
  private UserRole userRole;

  @JsonProperty("last_active_date")
  private Instant lastActiveDate;
}
