package com.opensourcehustlers.opensourcehustlersbackend.domain.tag;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.opensourcehustlers.opensourcehustlersbackend.domain.post.Post;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "tags")
public class Tag {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "Tag name must be provided")
  private String name;

  @NotBlank(message = "Tag color must be provided")
  private String color;

  @ManyToMany(mappedBy = "tags")
  @JsonBackReference
  private List<Post> posts;
}
