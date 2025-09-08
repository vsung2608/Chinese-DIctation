package com.chinese_dictation.service;

import com.chinese_dictation.model.dto.request.SentenceReportRequest;
import com.chinese_dictation.model.dto.response.DataPagedResponse;
import com.chinese_dictation.model.dto.response.SentenceReportResponse;
import com.chinese_dictation.model.enums.ReportStatus;

public interface ISentenceReportService {
    SentenceReportResponse reportSentence(Long userId, SentenceReportRequest request);
    SentenceReportResponse handleReport(Long reportId, ReportStatus reportStatus);
    DataPagedResponse<SentenceReportResponse> getSentenceReportPaged(int page, int size);
}
