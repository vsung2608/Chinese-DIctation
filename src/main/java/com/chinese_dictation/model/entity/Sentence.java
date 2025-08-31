package com.chinese_dictation.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id", nullable = false)
    private Lesson lesson;

    @Column(name = "sentence_order", nullable = false)
    private Integer sentenceOrder;

    @Column(name = "chinese_text", nullable = false, columnDefinition = "TEXT")
    private String chineseText;

    @Column(name = "pinyin_text", columnDefinition = "TEXT")
    private String pinyinText;

    @Column(name = "vietnamese_translation", columnDefinition = "TEXT")
    private String vietnameseTranslation;

    @Column(name = "start_time_seconds")
    private Double startTimeSeconds;

    @Column(name = "end_time_seconds")
    private Double endTimeSeconds;
}
