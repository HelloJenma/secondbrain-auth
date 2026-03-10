package com.secondbrain.auth.service;

import com.secondbrain.auth.dto.UserResponse;
import com.secondbrain.auth.dto.UserSettingsRequest;
import com.secondbrain.auth.dto.UserSettingsResponse;
import com.secondbrain.auth.model.User;
import com.secondbrain.auth.model.UserSettings;
import com.secondbrain.auth.repository.UserRepository;
import com.secondbrain.auth.repository.UserSettingsRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserSettingsRepository userSettingsRepository;

    public UserService(UserRepository userRepository, UserSettingsRepository userSettingsRepository) {
        this.userRepository = userRepository;
        this.userSettingsRepository = userSettingsRepository;
    }

    public UserResponse getUser(String userId) {
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserResponse(
                user.getId().toString(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }

    public UserSettingsResponse getUserSettings(String userId) {
        UserSettings settings = userSettingsRepository.findByUser_Id(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("Settings not found"));

        return new UserSettingsResponse(settings.getModuleOrder(), settings.getHiddenModules());
    }

    public UserSettingsResponse updateUserSettings(String userId, UserSettingsRequest userSettingsRequest) {
        UserSettings settings = userSettingsRepository.findByUser_Id(UUID.fromString(userId))
                .orElseThrow(() -> new RuntimeException("Settings not found"));

        settings.setModuleOrder(userSettingsRequest.moduleOrder());
        settings.setHiddenModules(userSettingsRequest.hiddenModules());
        userSettingsRepository.save(settings);

        return new UserSettingsResponse(settings.getModuleOrder(), settings.getHiddenModules());
    }
}
