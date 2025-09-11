package com.chinese_dictation.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record SentenceRequest(
        Integer sentenceOrder,
        String chineseText,
        String pinyinText,
        String vietnameseTranslation,
        Double startTimeSeconds,
        Double endTimeSeconds
) {
}
