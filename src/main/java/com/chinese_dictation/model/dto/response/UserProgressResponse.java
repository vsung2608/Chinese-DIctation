package com.chinese_dictation.model.dto.response;

import com.chinese_dictation.model.enums.ProgressStatus;

public record UserProgressResponse (
        Long id,
        ProgressStatus status,
        Integer currentSentenceIndex,
        Integer totalAttempts,
        Long totalTimeSpentSeconds
){
}
