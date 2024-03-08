package com.opensourcehustlers.opensourcehustlersbackend.domain.user;

import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.SecurityUser;
import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Builder
@Data
@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

  private final PasswordEncoder passwordEncoder;
  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
        .findByEmail(username)
        .map(SecurityUser::new)
        .orElseThrow(() -> new UserNotFoundException(username));
  }
}
