package com.chinese_dictation.model.dto.response;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String username,
        String fullName,
        LocalDateTime createdAt,
        LocalDateTime lastLogin,
        String avatarUrl,
        String status,
        String countryName,
        String countryCode
) {
}
