package com.chinese_dictation.model.dto.response;

import com.chinese_dictation.model.entity.Sentence;
import com.chinese_dictation.model.entity.Users;
import com.chinese_dictation.model.enums.ReportStatus;

import java.time.LocalDateTime;

public record SentenceReportResponse(
        Long id,
        String reason,
        ReportStatus status,
        String userName,
        String userAvatar,
        SentenceResponse sentence,
        String createdAt,
        String updatedAt,
        String handledAt
) {
}
