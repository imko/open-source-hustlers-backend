package com.opensourcehustlers.opensourcehustlersbackend;

import com.opensourcehustlers.opensourcehustlersbackend.model.Role;
import com.opensourcehustlers.opensourcehustlersbackend.model.User;
import com.opensourcehustlers.opensourcehustlersbackend.repository.RoleRepository;
import com.opensourcehustlers.opensourcehustlersbackend.repository.UserRepository;
import java.util.HashSet;
import java.util.Set;
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
  public CommandLineRunner run(
      RoleRepository roleRepository,
      UserRepository userRepository,
      PasswordEncoder passwordEncoder) {
    return args -> {
      if (roleRepository.findByAuthority("ADMIN").isPresent()) {
        return;
      }

      Role adminRole = roleRepository.save(Role.builder().authority("ADMIN").build());
      Role userRole = roleRepository.save(Role.builder().authority("USER").build());

      Set<Role> roles = new HashSet<>();
      roles.add(adminRole);

      User admin =
          User.builder()
              .email("admin@admin.com")
              .username("admin_user")
              .password(passwordEncoder.encode("password"))
              .roles(roles)
              .build();
      userRepository.save(admin);
    };
  }
}
