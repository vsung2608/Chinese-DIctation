package com.chinese_dictation.service;

import com.chinese_dictation.model.dto.request.NewCommentRequest;
import com.chinese_dictation.model.dto.request.UpdateCommentRequest;
import com.chinese_dictation.model.dto.response.CommentResponse;
import com.chinese_dictation.model.dto.response.DataPagedResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ICommentService {
    CommentResponse createComment(MultipartFile attachImage, NewCommentRequest request);
    CommentResponse updateComment(Long id, UpdateCommentRequest request);
    DataPagedResponse<CommentResponse> getComment(Long lessonId, int page, int size);
    DataPagedResponse<CommentResponse> getReplyComment(Long parentCommentId, int page, int size);
    void deleteComment(Long commentId);
}
