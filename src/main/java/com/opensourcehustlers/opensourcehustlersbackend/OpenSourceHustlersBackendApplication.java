package com.opensourcehustlers.opensourcehustlersbackend;

import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.User;
import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.UserRepository;
import com.opensourcehustlers.opensourcehustlersbackend.domain.auth.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OpenSourceHustlersBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(OpenSourceHustlersBackendApplication.class, args);
  }

  @Bean
  public CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return args -> {
      User admin =
          User.builder()
              .email("admin@admin.com")
              .displayName("admin")
              .userRole(UserRole.ADMIN)
              .password(passwordEncoder.encode("password"))
              .build();
      User user =
          User.builder()
              .email("user@user.com")
              .displayName("user")
              .userRole(UserRole.USER)
              .password(passwordEncoder.encode("password"))
              .build();

      userRepository.save(admin);
      userRepository.save(user);
    };
  }
}
