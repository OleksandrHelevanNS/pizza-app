package com.nerdysoft.authservice.dto;

import com.nerdysoft.common.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class AuthUserResponse {
    private Role role;
    private String token;
    private Date expiresIn;
}
