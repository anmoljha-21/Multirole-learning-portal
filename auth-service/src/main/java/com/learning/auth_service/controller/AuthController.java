package com.learning.auth_service.controller;

import com.learning.auth_service.dto.AuthRequest;
import com.learning.auth_service.dto.AuthResponse;
import com.learning.auth_service.dto.RegisterRequest;
import com.learning.auth_service.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        return authService.validateToken(token);
    }
}
