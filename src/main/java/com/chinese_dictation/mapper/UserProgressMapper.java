package com.chinese_dictation.mapper;

import com.chinese_dictation.model.dto.request.UserProgressRequest;
import com.chinese_dictation.model.dto.response.UserProgressResponse;
import com.chinese_dictation.model.entity.UserProgress;
import org.springframework.stereotype.Component;

@Component
public class UserProgressMapper {
    public UserProgress toNewUserProgress(UserProgressRequest userProgress) {
        return UserProgress.builder()
                .status(userProgress.status())
                .currentSentenceIndex(userProgress.currentSentenceIndex())
                .totalAttempts(userProgress.totalAttempts())
                .totalTimeSpentSeconds(userProgress.totalTimeSpentSeconds())
                .build();
    }

    public UserProgressResponse toUserProgressResponse(UserProgress userProgress) {
        return new UserProgressResponse(
                userProgress.getId(),
                userProgress.getStatus(),
                userProgress.getCurrentSentenceIndex(),
                userProgress.getTotalAttempts(),
                userProgress.getTotalTimeSpentSeconds()
        );
    }
}
