package com.chinese_dictation.model.dto.response;

public record CommentResponse(
        Long id,
        Long lessonId,
        Long userId,
        String content,
        String userName,
        String userAvatarUrl,
        int replyCount,
        Long parentCommentId,
        String createdAt,
        String attachImageUrl
) {
}
