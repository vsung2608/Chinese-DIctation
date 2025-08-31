package com.chinese_dictation.model.dto.request;

public record SentenceRequest(
        Integer sentenceOrder,
        String chineseText,
        String pinyinText,
        String vietnameseTranslation,
        Double startTimeSeconds,
        Double endTimeSeconds
) {
}
