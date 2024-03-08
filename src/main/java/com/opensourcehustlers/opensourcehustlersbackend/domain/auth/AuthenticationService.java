package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.InvalidUserCredentialsException;
import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.UserAlreadyExistsException;
import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.UserNotFoundException;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
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
            .lastActiveDate(Instant.now())
            .build();

    User user = userRepository.save(userToCreate);

    return UserResponseDTO.builder()
        .userId(user.getId())
        .displayName(user.getDisplayName())
        .email(user.getEmail())
        .enabled(user.isEnabled())
        .userRole(user.getUserRole())
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
            .lastActiveDate(user.getLastActiveDate())
            .build();

    return AuthenticationResponseDTO.builder()
        .userResponseDTO(userResponseDTO)
        .accessToken(accessToken)
        .refreshToken(refreshToken)
        .build();
  }
}
