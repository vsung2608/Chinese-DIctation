package com.chinese_dictation.model.dto.response;

public record CategoryStatisticsRespoonse(
        Long categoryId,
        String categoryName,
        String description,
        String imageUrl,
        Boolean isActive,
        Long totalLessons,
        Long totalStudents,
        Long totalAttempts
) {
}
