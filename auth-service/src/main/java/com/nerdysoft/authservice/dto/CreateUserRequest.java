package com.nerdysoft.authservice.dto;

import lombok.Getter;

@Getter
public class CreateUserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
