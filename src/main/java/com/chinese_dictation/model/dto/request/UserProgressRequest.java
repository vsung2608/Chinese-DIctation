package com.chinese_dictation.model.dto.request;

import com.chinese_dictation.model.enums.ProgressStatus;

public record UserProgressRequest (
        Long lessonId,
        ProgressStatus status,
        Integer currentSentenceIndex,
        Integer totalAttempts,
        Long totalTimeSpentSeconds
){
}
