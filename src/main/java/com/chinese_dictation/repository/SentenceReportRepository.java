package com.chinese_dictation.repository;

import com.chinese_dictation.model.entity.SentenceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SentenceReportRepository extends JpaRepository<SentenceReport, Long> {

}
