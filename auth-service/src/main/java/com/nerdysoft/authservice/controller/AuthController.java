package com.nerdysoft.authservice.controller;

import com.nerdysoft.authservice.dto.AuthUserRequest;
import com.nerdysoft.authservice.dto.AuthUserResponse;
import com.nerdysoft.authservice.dto.CreateUserRequest;
import com.nerdysoft.authservice.dto.UserResponse;
import com.nerdysoft.authservice.service.UserService;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class AuthController {
    public final UserService userService;
    private final RSAPublicKey publicKey;

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> registerUser(@RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(userService.create(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthUserResponse> login(@RequestBody AuthUserRequest request) {
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }

    @GetMapping("/.well-known/jwks.json")
    public Map<String, Object> keys() {
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .keyID("auth-key-1")
                .algorithm(JWSAlgorithm.RS256)
                .build();

        return new JWKSet(rsaKey).toJSONObject();
    }

}
