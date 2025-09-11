package com.chinese_dictation.service;

import com.chinese_dictation.model.dto.request.UserProgressRequest;
import com.chinese_dictation.model.dto.response.UserProgressResponse;
import com.chinese_dictation.model.entity.UserProgress;

public interface IUserProgressService {
    UserProgress createUserProgress(Long userId, UserProgressRequest request);
    void updateUserProgress(UserProgressRequest request, Long progressId);
    UserProgressResponse getUserProgress(Long userId, Long lessonId);
    void completeLesson(Long progressId, Long timeCompleted);
}
