package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import com.opensourcehustlers.opensourcehustlersbackend.domain.user.User;
import com.opensourcehustlers.opensourcehustlersbackend.exception.auth.InvalidTokenException;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TokenService {

  private final TokenRepository tokenRepository;

  //  TODO: Remove this after testing.
  public List<Token> findAll() {
    return tokenRepository.findAll();
  }

  public Token findByToken(String token) {
    return tokenRepository.findByToken(token).orElseThrow(() -> new InvalidTokenException());
  }

  public Token save(User user, String token, TokenType tokenType, Instant expiredDate) {
    Token tokenObject =
        Token.builder()
            .user(user)
            .token(token)
            .tokenType(tokenType)
            .expired(false)
            .revoked(false)
            .expiredDate(expiredDate)
            .build();
    return tokenRepository.save(tokenObject);
  }

  public Token save(Token token) {
    return tokenRepository.save(token);
  }

  public void revoke(User user, TokenType tokenType) {
    List<Token> validTokens =
        tokenRepository.findAllByUserIdAndTokenTypeAndExpiredFalseAndRevokedFalse(
            user.getId(), tokenType);

    if (validTokens.isEmpty()) {
      return;
    }

    validTokens.forEach(
        token -> {
          token.setExpired(true);
          token.setRevoked(true);
        });

    tokenRepository.saveAll(validTokens);
  }

  public void delete(User user, TokenType tokenType) {
    tokenRepository.deleteAllByUserIdAndTokenType(user.getId(), tokenType);
  }
}
