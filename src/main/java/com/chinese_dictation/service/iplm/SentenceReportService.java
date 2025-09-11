package com.chinese_dictation.service.iplm;

import com.chinese_dictation.mapper.SentenceReportMapper;
import com.chinese_dictation.model.dto.request.SentenceReportRequest;
import com.chinese_dictation.model.dto.response.DataPagedResponse;
import com.chinese_dictation.model.dto.response.SentenceReportResponse;
import com.chinese_dictation.model.entity.Comment;
import com.chinese_dictation.model.entity.Sentence;
import com.chinese_dictation.model.entity.SentenceReport;
import com.chinese_dictation.model.entity.Users;
import com.chinese_dictation.model.enums.ReportStatus;
import com.chinese_dictation.repository.SentenceReportRepository;
import com.chinese_dictation.repository.SentenceRepository;
import com.chinese_dictation.repository.UserRepository;
import com.chinese_dictation.service.ISentenceReportService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SentenceReportService implements ISentenceReportService {
    private final SentenceReportRepository sentenceReportRepository;
    private final UserRepository userRepository;
    private final SentenceReportMapper sentenceReportMapper;
    private final SentenceRepository sentenceRepository;

    @Transactional
    @Override
    public SentenceReportResponse reportSentence(Long userId, SentenceReportRequest request) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(("Nguoi dung voi ID::%s khong ton tai".formatted(userId))));

        Sentence sentence = sentenceRepository.findById(request.sentenceId())
                .orElseThrow(() -> new EntityNotFoundException("Cau hoi voi ID::%s khong ton tai".formatted(request.sentenceId())));

        SentenceReport report = sentenceReportMapper.toSentenceReport(request);
        report.setUser(user);
        report.setSentence(sentence);
        return sentenceReportMapper.toSentenceReportResponse(sentenceReportRepository.save(report));
    }

    @Override
    public SentenceReportResponse handleReport(Long reportId, ReportStatus reportStatus) {
        SentenceReport report = sentenceReportRepository.findById(reportId)
                .orElseThrow(() -> new EntityNotFoundException("Bao cao voi ID::%s khong ton tai".formatted(reportId)));

        report.setStatus(reportStatus);
        report.setHandledAt(Instant.now());
        return sentenceReportMapper.toSentenceReportResponse(report);
    }

    @Override
    public DataPagedResponse<SentenceReportResponse> getSentenceReportPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<SentenceReportResponse> comments = sentenceReportRepository.findAllWithPagination(pageable);
        return new DataPagedResponse<>(
                page,
                comments.getTotalPages(),
                comments.getSize(),
                comments.getTotalElements(),
                comments.getContent()
        );
    }
}
