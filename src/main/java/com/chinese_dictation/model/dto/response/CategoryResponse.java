package com.chinese_dictation.model.dto.response;

public record CategoryResponse(
        Long id,
        String name,
        String imageUrl,
        String description
) {
}
