package com.opensourcehustlers.opensourcehustlersbackend.service;

import com.opensourcehustlers.opensourcehustlersbackend.model.Role;
import com.opensourcehustlers.opensourcehustlersbackend.model.User;
import com.opensourcehustlers.opensourcehustlersbackend.repository.RoleRepository;
import com.opensourcehustlers.opensourcehustlersbackend.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthenticationService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;
  private final PasswordEncoder passwordEncoder;

  public User register(String username, String password) {
    String encodedPassword = passwordEncoder.encode(password);
    Role userRole = roleRepository.findByAuthority("USER").get();
    Set<Role> roles = new HashSet<>();

    roles.add(userRole);
    User user =
        User.builder()
            .email(username)
            .username(username)
            .password(encodedPassword)
            .roles(roles)
            .build();

    return userRepository.save(user);
  }
}
