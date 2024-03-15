package com.opensourcehustlers.opensourcehustlersbackend.domain.auth;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {

  List<Token> findAllByUserIdAndTokenTypeAndExpiredFalseAndRevokedFalse(
      Long userId, TokenType tokenType);

  boolean existsByUserIdAndTokenTypeAndExpiredFalseAndRevokedFalse(
      Long userId, TokenType tokenType);

  Optional<Token> findByToken(String token);

  void deleteAllByUserIdAndTokenType(Long userId, TokenType tokenType);
}
