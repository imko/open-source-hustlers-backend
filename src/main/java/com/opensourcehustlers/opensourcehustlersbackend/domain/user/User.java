package com.opensourcehustlers.opensourcehustlersbackend.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.Token;
import com.opensourcehustlers.opensourcehustlersbackend.domain.skill.Skill;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(name = "users")
@EntityListeners(AuditingEntityListener.class)
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

  @ManyToMany
  @JoinTable(
      name = "skills_users",
      joinColumns = @JoinColumn(name = "skill_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  @JsonBackReference
  private List<Skill> skills;

  @OneToMany(mappedBy = "user")
  @JsonBackReference
  private List<Token> tokens;

  @JsonProperty("last_active_date")
  private Instant lastActiveDate;

  @CreatedDate
  @JsonProperty("created_date")
  private Instant createdDate;

  @LastModifiedDate
  @JsonProperty("last_modified_date")
  private Instant lastModifiedDate;

  @Version
  private int version;
}
