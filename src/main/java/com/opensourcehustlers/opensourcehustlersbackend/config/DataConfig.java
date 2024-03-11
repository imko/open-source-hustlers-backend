package com.opensourcehustlers.opensourcehustlersbackend.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
@EnableJpaAuditing
public class DataConfig {

  @Bean
  public AuditorAware<String> auditorAware() {
    /* Extract the email of the currently authenticated user from SecurityContextHolder. */
    return () ->
        Optional
            // Extract SecurityContext from SecurityContextHolder.
            .ofNullable(SecurityContextHolder.getContext())
            // Extract Authentication from SecurityContext.
            .map(SecurityContext::getAuthentication)
            // Check whether the user is authenticated or not.
            .filter(Authentication::isAuthenticated)
            // Extract the authenticated user's email from Authentication.
            .map(Authentication::getName);
  }
}
