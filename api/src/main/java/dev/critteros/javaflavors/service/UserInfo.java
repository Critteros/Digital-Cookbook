package dev.critteros.javaflavors.service;

import lombok.Builder;

@Builder
public record UserInfo(
        String sub,
        String preferredUsername,
        String nickname,
        String name,
        String email
) {
}
