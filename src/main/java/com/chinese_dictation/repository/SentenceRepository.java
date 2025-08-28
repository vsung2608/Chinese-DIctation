package com.chinese_dictation.repository;

import com.chinese_dictation.model.entity.Sentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentenceRepository extends JpaRepository<Sentence, Long> {
}
