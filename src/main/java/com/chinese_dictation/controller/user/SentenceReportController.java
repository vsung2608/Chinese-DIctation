package com.chinese_dictation.controller.user;

import com.chinese_dictation.model.dto.request.SentenceReportRequest;
import com.chinese_dictation.model.dto.response.SentenceReportResponse;
import com.chinese_dictation.model.entity.Users;
import com.chinese_dictation.service.iplm.SentenceReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/report")
public class SentenceReportController {
    private final SentenceReportService sentenceReportService;

    @PostMapping
    public ResponseEntity<SentenceReportResponse> report(
            @AuthenticationPrincipal Users user,
            @RequestBody final SentenceReportRequest request) {
        return ResponseEntity.ok(sentenceReportService.reportSentence(user.getId(), request));
    }
}
