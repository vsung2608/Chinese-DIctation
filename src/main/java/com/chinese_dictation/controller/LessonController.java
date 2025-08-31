package com.chinese_dictation.controller;

import com.chinese_dictation.model.dto.request.LessonRequest;
import com.chinese_dictation.model.dto.response.LessonResponse;
import com.chinese_dictation.model.enums.VocabularyLevel;
import com.chinese_dictation.service.iplm.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponse> getLesson(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    @GetMapping("/level")
    public ResponseEntity<List<LessonResponse>> getLessonByLevel(
            @RequestParam("level") VocabularyLevel level,
            @RequestParam("category-id") Long categoryId) {
        return ResponseEntity.ok(lessonService.getLessonByCategoryAndLevel(categoryId, level));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<LessonResponse> createLesson(
            @RequestPart LessonRequest lessonRequest,
            @RequestPart MultipartFile file) {
        return ResponseEntity.ok(lessonService.createLesson(lessonRequest, file));
    }
}
