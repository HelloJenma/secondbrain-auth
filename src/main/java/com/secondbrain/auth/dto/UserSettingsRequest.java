package com.secondbrain.auth.dto;

public record UserSettingsRequest(
        String moduleOrder,
        String hiddenModules
)
{}
