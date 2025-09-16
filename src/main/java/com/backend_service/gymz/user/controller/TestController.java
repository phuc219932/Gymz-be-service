package com.backend_service.gymz.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend_service.gymz.common.model.ResponseData;
import com.backend_service.gymz.user.model.UserEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Test Controller")
@Slf4j(topic = "TEST-CONTROLLER")
public class TestController {

    @GetMapping("/public")
    @Operation(summary = "Public endpoint - no authentication required")
    public ResponseData<String> publicEndpoint() {
        log.info("Public endpoint accessed");
        return new ResponseData<>(
            HttpStatus.OK.value(),
            "Success",
            "This is a public endpoint - no authentication required"
        );
    }

    @GetMapping("/protected")
    @Operation(summary = "Protected endpoint - JWT authentication required")
    public ResponseData<String> protectedEndpoint(@AuthenticationPrincipal UserEntity user) {
        log.info("Protected endpoint accessed by user: {}", user.getUsername());
        return new ResponseData<>(
            HttpStatus.OK.value(),
            "Success",
            "Hello " + user.getUsername() + "! This is a protected endpoint."
        );
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Admin endpoint - requires ADMIN role")
    public ResponseData<String> adminEndpoint(@AuthenticationPrincipal UserEntity user) {
        log.info("Admin endpoint accessed by user: {}", user.getUsername());
        return new ResponseData<>(
            HttpStatus.OK.value(),
            "Success",
            "Hello Admin " + user.getUsername() + "! This is an admin-only endpoint."
        );
    }

    @GetMapping("/user-info")
    @Operation(summary = "Get current user information")
    public ResponseData<String> getCurrentUser(@AuthenticationPrincipal UserEntity user) {
        log.info("User info requested by: {}", user.getUsername());
        
        String userInfo = String.format(
            "User: %s (ID: %d), Email: %s, Status: %s, Type: %s, Authorities: %s",
            user.getUsername(),
            user.getId(),
            user.getEmail(),
            user.getStatus(),
            user.getUserType(),
            user.getAuthorities().toString()
        );
            
        return new ResponseData<>(
            HttpStatus.OK.value(),
            "Success",
            userInfo
        );
    }
}
