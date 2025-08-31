package com.chinese_dictation.model.dto.request;

import com.chinese_dictation.model.enums.VocabularyLevel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.List;

public record LessonRequest(
        @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
        String titleChinese,

        @Size(min = 1, max = 255, message = "Title must be between 1 and 255 characters")
        String titleVietnamese,

        @Size(max = 5000, message = "Description must not exceed 5000 characters")
        String description,

        @NotNull
        Long categoryId,

        @Min(value = 0, message = "Display order must be non-negative")
        @Max(value = 9999, message = "Display order must not exceed 9999")
        Integer displayOrder,

        VocabularyLevel level,

        @Min(value = 0, message = "Total sentences must be non-negative")
        @Max(value = 1000, message = "Total sentences must not exceed 1000")
        Integer totalSentences,

        @Min(value = 1, message = "Duration must be at least 1 second")
        @Max(value = 18000, message = "Duration must not exceed 5 hours")
        Integer estimatedDurationSeconds,

        @Valid
        @Size(max = 1000, message = "Cannot have more than 1000 sentences")
        List<SentenceRequest> sentences
) {
}
