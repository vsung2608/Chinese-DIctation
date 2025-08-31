package com.chinese_dictation.repository;

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
    @EntityGraph("Lesson.withSentences")
    Optional<Lesson> findById(Long id);

    @Query("SELECT l FROM Lesson l WHERE l.category.id = :categoryId AND l.level = :level")
    List<Lesson> findAllCategoryAndLevel(
            @Param("categoryId") Long categoryId,
            @Param("level") VocabularyLevel level);
}
