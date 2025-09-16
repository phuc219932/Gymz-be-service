package com.backend_service.gymz.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend_service.gymz.common.model.ResponseData;
import com.backend_service.gymz.user.dto.Request.auth.SignInRequest;
import com.backend_service.gymz.user.model.UserEntity;
import com.backend_service.gymz.user.service.JwtService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/test")
@Tag(name = "JWT Test Controller")
@Slf4j(topic = "JWT-TEST-CONTROLLER")
@RequiredArgsConstructor
public class JwtTestController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/token-size")
    @Operation(summary = "Test JWT token size comparison")
    public ResponseData<TokenSizeComparison> testTokenSize(@RequestBody SignInRequest request) {
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserEntity user = (UserEntity) authentication.getPrincipal();
            
            // Generate optimized token (only roles)
            String optimizedToken = jwtService.generateAccessToken(
                user.getId(), 
                user.getUsername(), 
                user.getAuthorities()
            );

            // Calculate token info
            TokenSizeComparison comparison = new TokenSizeComparison();
            comparison.username = user.getUsername();
            comparison.userId = user.getId();
            comparison.totalAuthorities = user.getAuthorities().size();
            comparison.optimizedTokenLength = optimizedToken.length();
            comparison.optimizedToken = optimizedToken;
            comparison.message = "Token optimized - only roles included, permissions loaded from database";

            return new ResponseData<>(
                HttpStatus.OK.value(),
                "JWT Token Size Test Complete",
                comparison
            );

        } catch (Exception e) {
            log.error("Error testing token size: {}", e.getMessage());
            return new ResponseData<>(
                HttpStatus.BAD_REQUEST.value(),
                "Authentication failed: " + e.getMessage()
            );
        }
    }

    private static class TokenSizeComparison {
        public String username;
        public Long userId;
        public int totalAuthorities;
        public int optimizedTokenLength;
        public String optimizedToken;
        public String message;
    }
}
