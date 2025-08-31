package com.chinese_dictation.model.dto.request;

import java.time.LocalDateTime;


public record UserRequest(
        Long id,
        String username,
        String fullName,
        String avatarUrl
) {
}
