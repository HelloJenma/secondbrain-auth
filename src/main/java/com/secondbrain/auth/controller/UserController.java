package com.secondbrain.auth.controller;

import com.secondbrain.auth.config.JwtConfig;
import com.secondbrain.auth.dto.UserResponse;
import com.secondbrain.auth.dto.UserSettingsRequest;
import com.secondbrain.auth.dto.UserSettingsResponse;
import com.secondbrain.auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtConfig jwtConfig;

    public UserController(UserService userService, JwtConfig jwtConfig) {
        this.userService = userService;
        this.jwtConfig = jwtConfig;
    }

    private String getUserIdFromHeader(String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        return jwtConfig.extractUserId(token).toString();
    }

    @GetMapping("/user")
    public ResponseEntity<UserResponse> getUser(@RequestHeader("Authorization") String authHeader) {
    String userId = getUserIdFromHeader(authHeader);
    return ResponseEntity.ok(userService.getUser(userId));
    }

    @GetMapping("/settings")
    public ResponseEntity<UserSettingsResponse> getUserSettings(@RequestHeader("Authorization") String authHeader) {
        String userId = getUserIdFromHeader(authHeader);
        return ResponseEntity.ok(userService.getUserSettings(userId));
    }

    @PutMapping("/settings")
    public ResponseEntity<UserSettingsResponse> updateSettings (
            @RequestHeader ("Authorization") String authHeader,
            @RequestBody UserSettingsRequest userSettingsRequest) {
        String userId = getUserIdFromHeader(authHeader);
        return ResponseEntity.ok(userService.updateUserSettings(userId, userSettingsRequest));
    }


}
