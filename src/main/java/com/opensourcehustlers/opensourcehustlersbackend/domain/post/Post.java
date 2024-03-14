package com.opensourcehustlers.opensourcehustlersbackend.domain.post;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opensourcehustlers.opensourcehustlersbackend.domain.tag.Tag;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "posts")
@EntityListeners(AuditingEntityListener.class)
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "Title must be provided")
  private String title;

  @NotBlank(message = "Description must be provided")
  private String description;

  @NotBlank(message = "Content must be provided")
  @Column(columnDefinition = "TEXT")
  private String content;

  @NotBlank(message = "GitHub URL must be provided")
  @JsonProperty("github_url")
  private String githubUrl;

  @NotNull(message = "Post visibility must be provided")
  @Enumerated(EnumType.STRING)
  private PostVisibility visibility;

  @ManyToMany
  @JoinTable(
      name = "posts_tags",
      joinColumns = @JoinColumn(name = "tag_id"),
      inverseJoinColumns = @JoinColumn(name = "post_id"))
  @JsonBackReference
  private List<Tag> tags;

  @CreatedBy
  @JsonProperty("created_by")
  private String createdBy;

  @CreatedDate
  @JsonProperty("created_date")
  private Instant createdDate;

  @LastModifiedBy
  @JsonProperty("last_modified_by")
  private String lastModifiedBy;

  @LastModifiedDate
  @JsonProperty("last_modified_date")
  private Instant lastModifiedDate;

  @Version private int version;
}
