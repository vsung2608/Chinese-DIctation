package com.chinese_dictation.repository;

import com.chinese_dictation.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByLessonId(Long lessonId);

    List<Comment> findByParentCommentId(Long parentCommentId);
}
