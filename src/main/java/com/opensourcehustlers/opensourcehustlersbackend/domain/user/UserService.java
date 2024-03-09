package com.opensourcehustlers.opensourcehustlersbackend.domain.user;

import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.UserNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

@Builder
@Data
@AllArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;

  public List<UserResponseDTO> findAll() {
    return userRepository.findAll().stream()
        .map(
            user ->
                UserResponseDTO.builder()
                    .userId(user.getId())
                    .displayName(user.getDisplayName())
                    .email(user.getEmail())
                    .enabled(user.isEnabled())
                    .userRole(user.getUserRole())
                    .skills(user.getSkills())
                    .lastActiveDate(user.getLastActiveDate())
                    .build())
        .collect(Collectors.toList());
  }

  public UserResponseDTO findById(Long id) {
    return userRepository
        .findById(id)
        .map(
            user ->
                UserResponseDTO.builder()
                    .userId(user.getId())
                    .displayName(user.getDisplayName())
                    .email(user.getEmail())
                    .enabled(user.isEnabled())
                    .userRole(user.getUserRole())
                    .skills(user.getSkills())
                    .lastActiveDate(user.getLastActiveDate())
                    .build())
        .orElseThrow(() -> new UserNotFoundException(id));
  }

  public UserResponseDTO save(Long id, UserEditRequestDTO data) {
    return userRepository
        .findById(id)
        .map(
            existingUser -> {
              var userToUpdate =
                  User.builder()
                      .id(existingUser.getId())
                      .displayName(data.getDisplayName())
                      .email(existingUser.getEmail())
                      .password(existingUser.getPassword())
                      .enabled(data.getEnabled())
                      .userRole(existingUser.getUserRole())
                      .skills(data.getSkills())
                      .lastActiveDate(existingUser.getLastActiveDate())
                      .createdDate(existingUser.getCreatedDate())
                      .lastModifiedDate(existingUser.getLastModifiedDate())
                      .build();

              var updatedUser = userRepository.save(userToUpdate);

              return UserResponseDTO.builder()
                  .userId(updatedUser.getId())
                  .displayName(updatedUser.getDisplayName())
                  .email(updatedUser.getEmail())
                  .enabled(updatedUser.isEnabled())
                  .userRole(updatedUser.getUserRole())
                  .skills(updatedUser.getSkills())
                  .lastActiveDate(updatedUser.getLastActiveDate())
                  .build();
            })
        .orElseThrow(() -> new UserNotFoundException(id));
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }
}
