package com.nerdysoft.authservice.controller;

import com.nerdysoft.authservice.dto.AuthUserRequest;
import com.nerdysoft.authservice.dto.CreateUserRequest;
import com.nerdysoft.authservice.dto.UserResponse;
import com.nerdysoft.authservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AuthController {
    public final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserResponse> registerUser(@RequestBody CreateUserRequest request) {
        return new ResponseEntity<>(userService.create(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthUserRequest request) {
        return new ResponseEntity<>(userService.login(request), HttpStatus.OK);
    }

}
