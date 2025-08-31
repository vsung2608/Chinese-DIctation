package com.chinese_dictation.mapper;

import com.chinese_dictation.model.dto.request.LessonRequest;
import com.chinese_dictation.model.dto.response.LessonResponse;
import com.chinese_dictation.model.entity.Lesson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LessonMapper {
    private final SentenceMapper sentenceMapper;

    public LessonResponse toLessonResponse(Lesson lesson) {
        return new LessonResponse(
                lesson.getId(),
                lesson.getTitleChinese(),
                lesson.getTitleVietnamese(),
                lesson.getDescription(),
                lesson.getAudioFilePath(),
                lesson.getLevel(),
                lesson.getDisplayOrder(),
                lesson.getTotalSentences(),
                lesson.getEstimatedDurationSeconds(),
                lesson.getSentences().stream()
                        .map(sentenceMapper::toResponse)
                        .toList()
        );
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
                .sentences(request.sentences().stream()
                        .map(sentenceMapper::toEntity)
                        .toList())
                .build();
    }
}
