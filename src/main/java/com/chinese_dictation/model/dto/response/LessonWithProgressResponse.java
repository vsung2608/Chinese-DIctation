package com.chinese_dictation.model.dto.response;

import com.chinese_dictation.model.enums.VocabularyLevel;

public record LessonWithProgressResponse (
        Long id,
        String titleChinese,
        String titleVietnamese,
        VocabularyLevel level,
        Integer totalSentences,
        Integer estimatedDurationSeconds,
        String status
){
}
