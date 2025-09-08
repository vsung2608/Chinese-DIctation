package com.chinese_dictation.model.dto.request;

public record SentenceReportRequest(
        String title,
        String reason,
        Long sentenceId
) {
}
