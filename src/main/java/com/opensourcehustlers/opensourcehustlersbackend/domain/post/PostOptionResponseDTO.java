package com.opensourcehustlers.opensourcehustlersbackend.domain.post;

import com.opensourcehustlers.opensourcehustlersbackend.domain.tag.Tag;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PostOptionResponseDTO {

  private List<Tag> tags;

  private List<PostVisibility> visibilities;
}
