package com.chinese_dictation.service.iplm;

import com.chinese_dictation.mapper.CommentMapper;
import com.chinese_dictation.model.dto.request.NewCommentRequest;
import com.chinese_dictation.model.dto.request.UpdateCommentRequest;
import com.chinese_dictation.model.dto.response.CommentResponse;
import com.chinese_dictation.model.dto.response.DataPagedResponse;
import com.chinese_dictation.model.dto.response.FileUploadResponse;
import com.chinese_dictation.model.entity.Comment;
import com.chinese_dictation.model.entity.Lesson;
import com.chinese_dictation.model.entity.Sentence;
import com.chinese_dictation.model.entity.Users;
import com.chinese_dictation.repository.CommentRepository;
import com.chinese_dictation.repository.LessonRepository;
import com.chinese_dictation.repository.UserRepository;
import com.chinese_dictation.service.ICommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final LessonRepository lessonRepository;
    private final CommentMapper commentMapper;
    private final CloudinaryService cloudinaryService;

    @Transactional
    @Override
    public CommentResponse createComment(MultipartFile attachImage, NewCommentRequest request) {
        Users user = userRepository.findById(request.userId())
                .orElseThrow(() -> new EntityNotFoundException("User Not Found"));

        Lesson lesson = lessonRepository.findById(request.lessonId())
                .orElseThrow(() -> new EntityNotFoundException("Lesson Not Found"));

        FileUploadResponse fileUploadResponse = null;
        if(attachImage != null && !attachImage.isEmpty()){
            try {
                fileUploadResponse = cloudinaryService.uploadImage(attachImage);
            } catch (IOException e) {
                log.error("Failed to upload image", e);
                throw new RuntimeException(e);
            }
        }

        if(request.parentCommentId() != null){
            Comment parentComment = commentRepository.findById(request.parentCommentId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent comment not found"));

            parentComment.setReplyCount(parentComment.getReplyCount() + 1);
        }

        try {
            Comment comment = commentMapper.CommentRequestToComment(request);
            comment.setUser(user);
            comment.setLesson(lesson);
            if (fileUploadResponse != null) {
                comment.setAttachIamgeUrl(fileUploadResponse.secureUrl());
            }
            return commentMapper.CommentToCommentResponse(commentRepository.save(comment));
        }catch (Exception e){
            log.error("Failed to create comment", e);
            if (fileUploadResponse != null) {
                try {
                    cloudinaryService.deleteFile(fileUploadResponse.publicId(), "image");
                    log.info("Successfully cleaned up uploaded file: {}", fileUploadResponse.secureUrl());
                } catch (Exception cleanupException) {
                    log.warn("Failed to cleanup uploaded file: {}", fileUploadResponse.secureUrl(), cleanupException);
                }
            }
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public CommentResponse updateComment(Long id, UpdateCommentRequest request) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Khong tim thay binh luan co ID::%s".formatted(id)));

        comment.setContent(request.content());
        comment.setAttachIamgeUrl(request.attachImageUrl());
        return commentMapper.CommentToCommentResponse(commentRepository.save(comment));
    }

    @Override
    public DataPagedResponse<CommentResponse> getComment(Long lessonId, int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Comment> comments = commentRepository.findByLessonId(lessonId, pageable);
        return new DataPagedResponse<>(
                page,
                comments.getTotalPages(),
                comments.getSize(),
                comments.getTotalElements(),
                comments.getContent().stream()
                        .map(commentMapper::CommentToCommentResponse)
                        .toList()
        );
    }

    @Override
    public DataPagedResponse<CommentResponse> getReplyComment(Long parentCommentId, int page, int size){
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Comment> comments = commentRepository.findByParentCommentId(parentCommentId, pageable);
        return new DataPagedResponse<>(
                page,
                comments.getTotalPages(),
                comments.getSize(),
                comments.getTotalElements(),
                comments.getContent().stream()
                        .map(commentMapper::CommentToCommentResponse)
                        .toList()
        );
    }

    @Override
    public void deleteComment(Long commentId) {
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Khong tim thay binh luan co ID::%s".formatted(commentId)));

        commentRepository.delete(comment);
    }
}
