package com.chinese_dictation.controller.admin;

import com.chinese_dictation.model.dto.response.DataPagedResponse;
import com.chinese_dictation.model.dto.response.SentenceReportResponse;
import com.chinese_dictation.service.iplm.SentenceReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/reports")
public class AdminSentenceReportController {
    private final SentenceReportService sentenceReportService;

    @GetMapping
    public ResponseEntity<DataPagedResponse<SentenceReportResponse>> getSentenceReport(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(sentenceReportService.getSentenceReportPaged(page, size));
    }
}
