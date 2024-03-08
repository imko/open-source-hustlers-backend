package com.opensourcehustlers.opensourcehustlersbackend.domain.skill;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.opensourcehustlers.opensourcehustlersbackend.domain.user.User;
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
@Table(name = "skills")
public class Skill {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotBlank(message = "skill name must be provided")
  private String name;

  @NotBlank(message = "skill color must be provided")
  private String color;

  @ManyToMany(mappedBy = "skills")
  @JsonBackReference
  private List<User> users;
}
