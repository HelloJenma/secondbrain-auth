package com.secondbrain.auth.controller;

import com.secondbrain.auth.config.JwtConfig;
import com.secondbrain.auth.dto.AuthResponse;
import com.secondbrain.auth.dto.LoginRequest;
import com.secondbrain.auth.dto.RegisterRequest;
import com.secondbrain.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;
    private final JwtConfig jwtConfig;

    public AuthController(final AuthService authService, final JwtConfig jwtConfig) {
        this.authService = authService;
        this.jwtConfig = jwtConfig;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<Map<String, String>> validate(@RequestHeader ("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String userId = jwtConfig.extractUserId(token).toString();
        return ResponseEntity.ok(Map.of("userId", userId, "valid", "true"));
    }


}
