package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import com.opensourcehustlers.opensourcehustlersbackend.domain.user.User;
import com.opensourcehustlers.opensourcehustlersbackend.domain.user.UserRepository;
import com.opensourcehustlers.opensourcehustlersbackend.domain.user.UserResponseDTO;
import com.opensourcehustlers.opensourcehustlersbackend.domain.user.UserRole;
import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.InvalidUserCredentialsException;
import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.UserAlreadyExistsException;
import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.UserNotFoundException;
import java.time.Instant;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional
@Service
public class AuthenticationService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;

  /* Registers a user with USER role. */
  public UserResponseDTO register(RegistrationRequestDTO data) {
    if (userRepository.existsByEmail(data.getEmail())) {
      throw new UserAlreadyExistsException(data.getEmail());
    }

    User userToCreate =
        User.builder()
            .displayName(data.getEmail())
            .email(data.getEmail())
            .password(passwordEncoder.encode(data.getPassword()))
            .enabled(true)
            .userRole(UserRole.USER)
            .skills(Collections.emptyList())
            .lastActiveDate(Instant.now())
            .build();

    User user = userRepository.save(userToCreate);

    return UserResponseDTO.builder()
        .userId(user.getId())
        .displayName(user.getDisplayName())
        .email(user.getEmail())
        .enabled(user.isEnabled())
        .userRole(user.getUserRole())
        .skills(user.getSkills())
        .lastActiveDate(user.getLastActiveDate())
        .build();
  }

  public AuthenticationResponseDTO login(AuthenticationRequestDTO data) {
    User user =
        userRepository
            .findByEmail(data.getEmail())
            .orElseThrow(() -> new UserNotFoundException(data.getEmail()));

    if (!passwordEncoder.matches(data.getPassword(), user.getPassword())) {
      throw new InvalidUserCredentialsException();
    }

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword()));

    String accessToken = jwtService.generateJwtAccessToken(authentication);
    String refreshToken = jwtService.generateJwtRefreshToken(authentication);

    UserResponseDTO userResponseDTO =
        UserResponseDTO.builder()
            .userId(user.getId())
            .displayName(user.getDisplayName())
            .email(user.getEmail())
            .enabled(user.isEnabled())
            .userRole(user.getUserRole())
            .skills(user.getSkills())
            .lastActiveDate(Instant.now())
            .build();

    return AuthenticationResponseDTO.builder()
        .userResponseDTO(userResponseDTO)
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }
}
