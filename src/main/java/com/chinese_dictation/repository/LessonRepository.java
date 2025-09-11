package com.chinese_dictation.repository;

import com.chinese_dictation.model.dto.response.LessonWithProgressResponse;
import com.chinese_dictation.model.entity.Lesson;
import com.chinese_dictation.model.enums.VocabularyLevel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Optional<Lesson> findById(Long id);

    @EntityGraph("Lesson.withSentences")
    Optional<Lesson> findWithSentencesById(Long id);

    @Query("SELECT new com.chinese_dictation.model.dto.response.LessonWithProgressResponse(" +
            "l.id, l.titleChinese, l.titleVietnamese, l.level, l.totalSentences, l.estimatedDurationSeconds, " +
            "COALESCE(CAST(up.status AS string), 'NOT_STARTED')) " +
            "FROM Lesson l " +
            "LEFT JOIN UserProgress up ON l.id = up.lesson.id AND up.user.id = :userId " +
            "WHERE l.category.id = :categoryId AND l.level = :level")
    List<LessonWithProgressResponse> findAllCategoryAndLevel(
            @Param("categoryId") Long categoryId,
            @Param("level") VocabularyLevel level,
            @Param("userId") Long userId);
}
