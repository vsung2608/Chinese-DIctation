package com.chinese_dictation.model.dto.response;

import com.chinese_dictation.model.enums.VocabularyLevel;

import java.util.List;

public record LessonResponse (
        Long id,
        String titleChinese,
        String titleVietnamese,
        String description,
        String audioFilePath,
        VocabularyLevel level,
        Integer displayOrder,
        Integer totalSentences,
        Integer estimatedDurationSeconds,
        List<SentenceResponse> sentences
){
}
