package com.nerdysoft.authservice.service;

import com.nerdysoft.authservice.dto.AuthUserRequest;
import com.nerdysoft.authservice.dto.CreateUserRequest;
import com.nerdysoft.authservice.dto.UserResponse;

import java.util.UUID;


public interface UserService {
    UserResponse create(CreateUserRequest request);
    String login(AuthUserRequest request);
    UserResponse findById(UUID id);
}
