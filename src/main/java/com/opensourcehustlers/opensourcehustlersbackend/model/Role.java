package com.opensourcehustlers.opensourcehustlersbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Role implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String authority;

  @Override
  public String getAuthority() {
    return authority;
  }
}
