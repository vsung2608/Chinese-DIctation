package com.chinese_dictation.model.dto.response;

import com.chinese_dictation.model.entity.Lesson;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record SentenceResponse(
        Long id,
        Integer sentenceOrder,
        String chineseText,
        String pinyinText,
        String vietnameseTranslation,
        Double startTimeSeconds,
        Double endTimeSeconds
) {
}
