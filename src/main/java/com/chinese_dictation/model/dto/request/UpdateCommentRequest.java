package com.chinese_dictation.model.dto.request;

public record UpdateCommentRequest(
        String content,
        String attachImageUrl
) {
}
