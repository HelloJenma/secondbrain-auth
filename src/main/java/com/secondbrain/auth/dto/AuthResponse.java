package com.secondbrain.auth.dto;

public record AuthResponse(
        String token,
        String userId,
        String email
) {}
