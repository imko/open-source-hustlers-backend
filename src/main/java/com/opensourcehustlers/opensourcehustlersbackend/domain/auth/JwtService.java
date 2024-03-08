package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import java.time.Instant;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtService {

  private final JwtEncoder jwtEncoder;
  private final JwtDecoder jwtDecoder;

  @Value("${application.security.jwt.issuer}")
  private String JWT_ISSUER;

  @Value("${application.security.jwt.access-token.expiration}")
  private long JWT_ACCESS_TOKEN_EXPIRATION;

  @Value("${application.security.jwt.refresh-token.expiration}")
  private long JWT_REFRESH_TOKEN_EXPIRATION;

  public String generateJwtAccessToken(Authentication authentication) {
    return generateJwt(authentication, JWT_ACCESS_TOKEN_EXPIRATION);
  }

  public String generateJwtRefreshToken(Authentication authentication) {
    return generateJwt(authentication, JWT_REFRESH_TOKEN_EXPIRATION);
  }

  private String generateJwt(Authentication authentication, long expiration) {
    Instant now = Instant.now();
    String scope =
        authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));
    JwtClaimsSet jwtClaimsSet =
        JwtClaimsSet.builder()
            .issuer(JWT_ISSUER)
            .issuedAt(now)
            .expiresAt(now.plusMillis(expiration))
            .subject(authentication.getName())
            .claim("roles", scope)
            .build();

    return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
  }
}
