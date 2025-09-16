package com.backend_service.gymz.user.controller;

import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.backend_service.gymz.user.dto.Request.auth.SignInRequest;
import com.backend_service.gymz.user.dto.Response.auth.TokenResponse;
import com.backend_service.gymz.user.service.AuthenticationService;


@RestController
@RequestMapping("/api/auth")
@Slf4j(topic = "AUTH-CONTROLLER")
@Tag(name = "AuthController")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/accessToken")
    public TokenResponse getAccessToken(@RequestBody SignInRequest request) {

        log.info("Access token request");

        return authenticationService.getAccessToken(request);
    }


    @PostMapping("/refeshToken")
    public TokenResponse getRefreshToken(@RequestBody String request) {

        log.info("Refresh token request");

        // return TokenResponse.builder()
        // .accessToken("DUMMY-NEW-ACCESS-TOKEN")
        // .refreshToken("DUMMY_REFRESH_TOKEN")
        // .build();

        return authenticationService.getRefreshToken(request);

      
    }
    
    
}
