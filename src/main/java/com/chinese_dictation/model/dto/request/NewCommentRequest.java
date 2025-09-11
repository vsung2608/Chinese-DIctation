package com.chinese_dictation.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NewCommentRequest (
        Long lessonId,
        String content,
        Long parentCommentId
){
}
