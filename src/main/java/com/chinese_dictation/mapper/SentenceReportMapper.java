package com.chinese_dictation.mapper;


import com.chinese_dictation.model.dto.request.SentenceReportRequest;
import com.chinese_dictation.model.dto.response.SentenceReportResponse;
import com.chinese_dictation.model.entity.SentenceReport;
import com.chinese_dictation.model.enums.ReportStatus;
import com.chinese_dictation.utils.TimeFormatterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SentenceReportMapper {
    private final SentenceMapper sentenceMapper;
    private final TimeFormatterUtil timeFormatterUtil;

    public SentenceReport toSentenceReport(SentenceReportRequest sentenceReport) {
        return SentenceReport.builder()
                .reason(sentenceReport.reason())
                .status(ReportStatus.PENDING)
                .title(sentenceReport.title())
                .build();
    }

    public SentenceReportResponse toSentenceReportResponse(SentenceReport sentenceReport) {
        return new SentenceReportResponse(
                sentenceReport.getId(),
                sentenceReport.getTitle(),
                sentenceReport.getReason(),
                sentenceReport.getStatus(),
                sentenceReport.getUser().getFullName(),
                sentenceReport.getUser().getAvatarUrl(),
                sentenceReport.getSentence().getId(),
                sentenceReport.getCreatedAt(),
                sentenceReport.getUpdatedAt(),
                sentenceReport.getHandledAt()
        );
    }
}
