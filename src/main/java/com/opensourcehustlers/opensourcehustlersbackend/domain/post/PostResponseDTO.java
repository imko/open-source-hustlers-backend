package com.opensourcehustlers.opensourcehustlersbackend.domain.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.opensourcehustlers.opensourcehustlersbackend.domain.tag.Tag;
import java.time.Instant;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostResponseDTO {

  private Long id;

  private String title;

  private String description;

  private String content;

  @JsonProperty("github_url")
  private String githubUrl;

  private PostVisibility visibility;

  private List<Tag> tags;

  @JsonProperty("created_by")
  private String createdBy;

  @JsonProperty("created_date")
  private Instant createdDate;

  @JsonProperty("last_modified_date")
  private Instant lastModifiedDate;
}
