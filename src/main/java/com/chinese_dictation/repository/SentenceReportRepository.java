package com.chinese_dictation.repository;

import com.chinese_dictation.model.dto.response.SentenceReportResponse;
import com.chinese_dictation.model.entity.SentenceReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SentenceReportRepository extends JpaRepository<SentenceReport, Long> {
    @Query("SELECT new com.chinese_dictation.model.dto.response.SentenceReportResponse(" +
            "sr.id, " +
            "sr.title, " +
            "sr.reason, " +
            "sr.status, " +
            "u.fullName, " +
            "u.avatarUrl, " +
            "sr.sentence.id, " +
            "sr.createdAt, " +
            "sr.updatedAt, " +
            "sr.handledAt) " +
            "FROM SentenceReport sr " +
            "JOIN sr.user u " +
            "ORDER BY sr.createdAt DESC")
    Page<SentenceReportResponse> findAllWithPagination(Pageable pageable);

}
