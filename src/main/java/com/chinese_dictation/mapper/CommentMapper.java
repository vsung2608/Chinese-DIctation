package com.chinese_dictation.mapper;

import com.chinese_dictation.model.dto.request.NewCommentRequest;
import com.chinese_dictation.model.dto.response.CommentResponse;
import com.chinese_dictation.model.entity.Comment;
import com.chinese_dictation.utils.TimeFormatterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    private final TimeFormatterUtil timeFormatter;

    public Comment CommentRequestToComment(NewCommentRequest request) {
        return Comment.builder()
                .content(request.content())
                .parentCommentId(request.parentCommentId())
                .createdAt(Instant.now())
                .replyCount(0)
                .build();
    }

    public CommentResponse CommentToCommentResponse(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getLesson().getId(),
                comment.getUser().getId(),
                comment.getContent(),
                comment.getUser().getFullName(),
                comment.getUser().getAvatarUrl(),
                comment.getReplyCount(),
                comment.getParentCommentId(),
                timeFormatter.format(comment.getCreatedAt()),
                comment.getAttachIamgeUrl()
        );
    }
}
