package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import com.opensourcehustlers.opensourcehustlersbackend.domain.user.UserRepository;
import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Builder
@Data
@AllArgsConstructor
@Service
public class SecurityUserService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository
        .findByEmail(username)
        .map(SecurityUser::new)
        .orElseThrow(() -> new UserNotFoundException(username));
  }
}
