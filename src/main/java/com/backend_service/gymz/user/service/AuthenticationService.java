package com.backend_service.gymz.user.service;


import com.backend_service.gymz.user.dto.Request.auth.SignInRequest;
import com.backend_service.gymz.user.dto.Response.auth.TokenResponse;


public interface AuthenticationService {
    TokenResponse getAccessToken(SignInRequest request);
    
    TokenResponse getRefreshToken(String request);
}
