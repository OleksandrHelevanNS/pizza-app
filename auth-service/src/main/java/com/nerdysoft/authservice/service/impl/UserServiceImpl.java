package com.nerdysoft.authservice.service.impl;

import com.nerdysoft.authservice.dto.AuthUserRequest;
import com.nerdysoft.authservice.dto.AuthUserResponse;
import com.nerdysoft.authservice.dto.CreateUserRequest;
import com.nerdysoft.authservice.dto.UserResponse;
import com.nerdysoft.authservice.model.User;
import com.nerdysoft.authservice.repo.UserRepository;
import com.nerdysoft.authservice.security.JwtUtils;
import com.nerdysoft.authservice.service.UserService;
import com.nerdysoft.common.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public UserResponse create(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(Role.CLIENT);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User saved = userRepository.save(user);

        return UserResponse.builder()
                .id(saved.getId())
                .firstName(saved.getFirstName())
                .lastName(saved.getLastName())
                .email(saved.getEmail())
                .role(saved.getRole())
                .build();
    }

    @Override
    public UserResponse findById(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthUserResponse login(AuthUserRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException(request.email()));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token =  jwtUtils.generateToken(user.getEmail(), user.getRole().name());
        return new AuthUserResponse(user.getRole(), token, jwtUtils.calculateExpirationDate());
    }

}
