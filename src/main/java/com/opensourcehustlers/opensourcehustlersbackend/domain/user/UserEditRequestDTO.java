package com.opensourcehustlers.opensourcehustlersbackend.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opensourcehustlers.opensourcehustlersbackend.domain.skill.Skill;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserEditRequestDTO {

  @NotBlank(message = "Display name must be provided")
  @JsonProperty("display_name")
  private String displayName;

  @NotNull(message = "Enabled must be provided")
  private Boolean enabled;

  private List<Skill> skills;
}
