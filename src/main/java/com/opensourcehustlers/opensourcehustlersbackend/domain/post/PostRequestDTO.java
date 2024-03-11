package com.opensourcehustlers.opensourcehustlersbackend.domain.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opensourcehustlers.opensourcehustlersbackend.domain.tag.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostRequestDTO {

  @NotBlank(message = "Title must be provided")
  private String title;

  @NotBlank(message = "Description must be provided")
  private String description;

  @NotBlank(message = "Content must be provided")
  private String content;

  @NotBlank(message = "GitHub URL must be provided")
  @JsonProperty("github_url")
  private String githubUrl;

  @NotNull(message = "Post visibility must be provided")
  private PostVisibility visibility;

  private List<Tag> tags;
}
