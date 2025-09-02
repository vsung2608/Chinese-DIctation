package com.chinese_dictation.mapper;

import com.chinese_dictation.model.dto.request.SentenceRequest;
import com.chinese_dictation.model.dto.response.SentenceResponse;
import com.chinese_dictation.model.entity.Sentence;
import org.springframework.stereotype.Component;

@Component
public class SentenceMapper {
    public SentenceResponse toResponse(Sentence sentence) {
        return new SentenceResponse(
                sentence.getId(),
                sentence.getSentenceOrder(),
                sentence.getChineseText(),
                sentence.getPinyinText(),
                sentence.getVietnameseTranslation(),
                sentence.getStartTimeSeconds(),
                sentence.getEndTimeSeconds()
        );
    }

    public Sentence toEntity(SentenceRequest request) {
        return Sentence.builder()
                .chineseText(request.chineseText())
                .pinyinText(request.pinyinText())
                .vietnameseTranslation(request.vietnameseTranslation())
                .sentenceOrder(request.sentenceOrder())
                .startTimeSeconds(request.startTimeSeconds())
                .endTimeSeconds(request.endTimeSeconds())
                .build();
    }
}
