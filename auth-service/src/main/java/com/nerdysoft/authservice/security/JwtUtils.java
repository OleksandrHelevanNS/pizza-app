package com.nerdysoft.authservice.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtUtils {

    private final RSAPrivateKey privateKey;

    @Value("${security.expiration_in_hours}")
    private long expirationInHours;

    public Date calculateExpirationDate() {
        long expirationMillis = expirationInHours * 60 * 60 * 1000;
        return new Date(System.currentTimeMillis() + expirationMillis);
    }


    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", List.of(role.replace("ROLE_", "")))
                .setIssuedAt(new Date())
                .setExpiration(calculateExpirationDate())
                .setHeaderParam("kid", "auth-key-1")
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

}
