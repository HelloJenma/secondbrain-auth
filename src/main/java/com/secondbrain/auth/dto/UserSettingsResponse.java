package com.secondbrain.auth.dto;

public record UserSettingsResponse (
        String moduleOrder,
        String hiddenModules
){}
