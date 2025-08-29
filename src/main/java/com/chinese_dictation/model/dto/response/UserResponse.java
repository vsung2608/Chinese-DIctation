package com.chinese_dictation.model.dto.response;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String username,
        String fullName,
        Integer totalScore,
        Integer lessonsCompleted,
        LocalDateTime createdAt,
        LocalDateTime lastLogin,
        Boolean isActive
) {
}
