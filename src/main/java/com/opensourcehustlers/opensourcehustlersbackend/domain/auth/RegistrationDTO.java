package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationDTO {

  private String username;
  private String password;
}
