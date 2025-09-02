package com.chinese_dictation.model.dto.request;

public record NewCommentRequest (
        Long lessonId,
        Long userId,
        String content,
        String userName,
        String userAvatarUrl,
        Long parentCommentId,
        String attachIamgeUrl
){
}
