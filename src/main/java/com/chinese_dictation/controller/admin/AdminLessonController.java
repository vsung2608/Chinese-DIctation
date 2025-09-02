package com.chinese_dictation.controller.admin;

import com.chinese_dictation.model.dto.request.LessonRequest;
import com.chinese_dictation.model.dto.response.DataPagedResponse;
import com.chinese_dictation.model.dto.response.LessonResponse;
import com.chinese_dictation.service.iplm.LessonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/lessons")
public class AdminLessonController {
    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<DataPagedResponse<LessonResponse>> getAllLessons(
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "page", defaultValue = "1", required = false) int page
    ) {
        return ResponseEntity.ok(lessonService.getLessonPaged(size, page));
    }

    @PostMapping
    public ResponseEntity<LessonResponse> createLesson(
            @RequestPart("lesson") String lessonJson,
            @RequestPart("file") MultipartFile file) throws JsonProcessingException {
        LessonRequest lessonRequest = new ObjectMapper().readValue(lessonJson, LessonRequest.class);
        return ResponseEntity.ok(lessonService.createLesson(lessonRequest, file));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonResponse> updateLesson(@PathVariable Long id, @RequestBody LessonRequest request){
        return ResponseEntity.ok(lessonService.updateLesson(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLesson(@PathVariable Long id){
        lessonService.deleteLesson(id);
        return ResponseEntity.ok("Delete success");
    }
}
