package com.nerdysoft.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;

@Configuration
public class RsaKeyConfig {

    @Bean
    public RSAPrivateKey privateKey() throws Exception {
        return (RSAPrivateKey) loadKey("keys/private.pem", true);
    }

    @Bean
    public RSAPublicKey publicKey() throws Exception {
        return (RSAPublicKey) loadKey("keys/public.pem", false);
    }

    private Key loadKey(String path, boolean isPrivate) throws Exception {
        String key = new String(
                Objects.requireNonNull(
                        getClass().getClassLoader().getResourceAsStream(path)
                ).readAllBytes()
        );

        key = key
                .replaceAll("-----BEGIN (.*)-----", "")
                .replaceAll("-----END (.*)-----", "")
                .replaceAll("\\s", "");

        byte[] decoded = Base64.getDecoder().decode(key);
        KeyFactory kf = KeyFactory.getInstance("RSA");

        return isPrivate
                ? kf.generatePrivate(new PKCS8EncodedKeySpec(decoded))
                : kf.generatePublic(new X509EncodedKeySpec(decoded));
    }
}
