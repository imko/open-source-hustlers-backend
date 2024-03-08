package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "users")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(unique = true)
  @JsonProperty("display_name")
  private String displayName;

  @Column(unique = true)
  private String email;

  private String password;

  private boolean enabled;

  @NotNull(message = "user role must be provided")
  @Enumerated(EnumType.STRING)
  @JsonProperty("user_role")
  private UserRole userRole;

  @JsonProperty("last_active_date")
  private Instant lastActiveDate;

  @CreatedDate
  @JsonProperty("created_date")
  private Instant createdDate;

  @LastModifiedDate
  @JsonProperty("last_modified_date")
  private Instant lastModifiedDate;

  private int version;
}
