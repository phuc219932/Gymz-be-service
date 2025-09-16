package com.backend_service.gymz.user.dto.Request.auth;

import lombok.Getter;

@Getter
public class SignInRequest {
    private String username;
    private String password;
    private String deviceToken;
}
