package com.chinese_dictation.controller.user;

import com.chinese_dictation.model.dto.response.LessonResponse;
import com.chinese_dictation.model.enums.VocabularyLevel;
import com.chinese_dictation.service.iplm.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/lessons")
@RequiredArgsConstructor
public class UserLessonController {
    private final LessonService lessonService;

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponse> getDetailLesson(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    @GetMapping("/level")
    public ResponseEntity<List<LessonResponse>> getLessonByLevel(
            @RequestParam("level") VocabularyLevel level,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("userId") Long userId) {
        return ResponseEntity.ok(lessonService.getLessonByCategoryAndLevel(categoryId, level, userId));
    }
}
