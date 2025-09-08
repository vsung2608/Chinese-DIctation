package com.chinese_dictation.model.dto.response;

import com.chinese_dictation.model.enums.VocabularyLevel;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LessonResponse{
    private Long id;
    private String titleChinese;
    private String titleVietnamese;
    private String description;
    private String audioFilePath;
    private VocabularyLevel level;
    private Integer displayOrder;
    private Integer totalSentences;
    private Integer estimatedDurationSeconds;
    private List<SentenceResponse> sentences;
}
