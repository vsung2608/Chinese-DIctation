package com.chinese_dictation.controller.user;

import com.chinese_dictation.model.dto.response.LessonResponse;
import com.chinese_dictation.model.dto.response.LessonWithProgressResponse;
import com.chinese_dictation.model.entity.Users;
import com.chinese_dictation.model.enums.VocabularyLevel;
import com.chinese_dictation.service.iplm.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<LessonWithProgressResponse>> getLessonByLevel(
            @AuthenticationPrincipal Users user,
            @RequestParam("level") VocabularyLevel level,
            @RequestParam("categoryId") Long categoryId) {
        return ResponseEntity.ok(lessonService.getLessonByCategoryAndLevel(categoryId, level, user.getId()));
    }
}
