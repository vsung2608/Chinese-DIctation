package com.chinese_dictation.model.dto.response;

import com.chinese_dictation.model.enums.ReportStatus;

import java.time.Instant;

public record SentenceReportResponse(
        Long id,
        String title,
        String reason,
        ReportStatus status,
        String userName,
        String userAvatar,
        Long sentenceId,
        Instant createdAt,
        Instant updatedAt,
        Instant handledAt
) {
}
