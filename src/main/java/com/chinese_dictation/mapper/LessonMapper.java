package com.chinese_dictation.mapper;

import com.chinese_dictation.model.dto.request.LessonRequest;
import com.chinese_dictation.model.dto.response.LessonResponse;
import com.chinese_dictation.model.dto.response.LessonWithProgressResponse;
import com.chinese_dictation.model.entity.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class LessonMapper {
    private final SentenceMapper sentenceMapper;

    public LessonResponse toLessonWithSentencesResponse(Lesson lesson) {
        return LessonResponse.builder()
                .id(lesson.getId())
                .titleChinese(lesson.getTitleChinese())
                .titleVietnamese(lesson.getTitleVietnamese())
                .description(lesson.getDescription())
                .audioFilePath(lesson.getAudioFilePath())
                .level(lesson.getLevel())
                .displayOrder(lesson.getDisplayOrder())
                .totalSentences(lesson.getTotalSentences())
                .estimatedDurationSeconds(lesson.getEstimatedDurationSeconds())
                .sentences(lesson.getSentences().stream()
                        .map(sentenceMapper::toResponse)
                        .toList())
                .build();
    }

    public LessonResponse toLessonResponse(Lesson lesson) {
        return LessonResponse.builder()
                .id(lesson.getId())
                .titleChinese(lesson.getTitleChinese())
                .titleVietnamese(lesson.getTitleVietnamese())
                .description(lesson.getDescription())
                .audioFilePath(lesson.getAudioFilePath())
                .level(lesson.getLevel())
                .displayOrder(lesson.getDisplayOrder())
                .totalSentences(lesson.getTotalSentences())
                .estimatedDurationSeconds(lesson.getEstimatedDurationSeconds())
                .build();
    }

    public Lesson toLesson(LessonRequest request) {
        return Lesson.builder()
                .titleChinese(request.titleChinese())
                .titleVietnamese(request.titleVietnamese())
                .description(request.description())
                .level(request.level())
                .displayOrder(request.displayOrder())
                .totalSentences(request.totalSentences())
                .estimatedDurationSeconds(request.estimatedDurationSeconds())
                .sentences(new ArrayList<>())
                .build();
    }
}
