package com.opensourcehustlers.opensourcehustlersbackend.dto;

import com.opensourcehustlers.opensourcehustlersbackend.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

  private User user;
  private String jwt;
}
