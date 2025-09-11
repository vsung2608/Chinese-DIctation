package com.chinese_dictation.service.iplm;

import com.chinese_dictation.mapper.UserProgressMapper;
import com.chinese_dictation.model.dto.request.UserProgressRequest;
import com.chinese_dictation.model.dto.response.UserProgressResponse;
import com.chinese_dictation.model.entity.Lesson;
import com.chinese_dictation.model.entity.UserProgress;
import com.chinese_dictation.model.entity.Users;
import com.chinese_dictation.model.enums.ProgressStatus;
import com.chinese_dictation.repository.LessonRepository;
import com.chinese_dictation.repository.UserProgressRepository;
import com.chinese_dictation.repository.UserRepository;
import com.chinese_dictation.service.IUserProgressService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProgressService implements IUserProgressService {
    private final UserRepository userRepository;
    private final UserProgressRepository userProgressRepository;
    private final LessonRepository lessonRepository;
    private final UserProgressMapper userProgressMapper;

    @Override
    @Transactional
    public UserProgress createUserProgress(Long userId, UserProgressRequest request) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Users not found"));

        Lesson lesson = lessonRepository.findById(request.lessonId())
                .orElseThrow(() -> new EntityNotFoundException("Lessons not found"));

        UserProgress progress = userProgressMapper.toNewUserProgress(request);
        progress.setUser(user);
        progress.setLesson(lesson);
        return userProgressRepository.save(progress);
    }

    @Override
    @Transactional
    public void updateUserProgress(UserProgressRequest request, Long progressId) {
        UserProgress progress = userProgressRepository.findById(progressId)
                .orElseThrow(() -> new EntityNotFoundException("UserProgress not found"));

        progress.setCurrentSentenceIndex(request.currentSentenceIndex());
        progress.setTotalAttempts(request.totalAttempts());
        progress.setTotalTimeSpentSeconds(request.totalTimeSpentSeconds());
        userProgressRepository.save(progress);
    }

    @Override
    @Transactional
    public UserProgressResponse getUserProgress(Long userId, Long lessonId) {
        UserProgress progress = userProgressRepository.findByUserIdAndLessonId(userId, lessonId)
                .orElseGet(() -> {
                    UserProgressRequest request = new UserProgressRequest(
                            lessonId,
                            ProgressStatus.IN_PROGRESS,
                            0,
                            0,
                            0L
                    );
                    return createUserProgress(userId, request);
                });

        return userProgressMapper.toUserProgressResponse(progress);
    }

    @Override
    public void completeLesson(Long progressId, Long timeCompleted) {
        UserProgress progress = userProgressRepository.findById(progressId)
                .orElseThrow(() -> new EntityNotFoundException("UserProgress not found"));

        progress.setStatus(ProgressStatus.COMPLETED);
        progress.setCurrentSentenceIndex(0);
        progress.setTotalAttempts(progress.getTotalAttempts() + 1);
        progress.setTotalTimeSpentSeconds(progress.getTotalTimeSpentSeconds() + timeCompleted);
        userProgressRepository.save(progress);
    }
}
