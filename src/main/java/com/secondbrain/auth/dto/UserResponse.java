package com.secondbrain.auth.dto;

import java.time.LocalDateTime;

public record UserResponse (
        String userId,
        String email,
        LocalDateTime createdAt
){}
