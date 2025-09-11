package com.chinese_dictation.repository;

import com.chinese_dictation.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByLessonIdAndParentCommentIdIsNull(Long lessonId, Pageable pageable);

    Page<Comment> findByParentCommentId(Long parentCommentId, Pageable pageable);

    Page<Comment> findByOrderByCreatedAtDesc(Pageable pageable);
}
