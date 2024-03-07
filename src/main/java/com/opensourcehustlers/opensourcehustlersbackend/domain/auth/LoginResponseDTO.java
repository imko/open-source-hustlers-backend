package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

  private User user;
  private String jwt;
}
