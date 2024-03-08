package com.opensourcehustlers.opensourcehustlersbackend.utils;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RsaKeyProperties {

  private RSAPublicKey rsaPublicKey;
  private RSAPrivateKey rsaPrivateKey;

  public RsaKeyProperties() throws NoSuchAlgorithmException {
    KeyPair keyPair = KeyGeneratorUtility.generateRsaKey();
    rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
    rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
  }
}
