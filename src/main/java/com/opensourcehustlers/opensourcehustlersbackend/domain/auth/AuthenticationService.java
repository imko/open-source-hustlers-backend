package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

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

  public LoginResponseDTO login(String username, String password) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password));
    String token = tokenService.generateJwt(authentication);
    return new LoginResponseDTO(userRepository.findByEmail(username).get(), token);
  }
}
