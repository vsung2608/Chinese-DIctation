package com.chinese_dictation.service;

import com.chinese_dictation.model.dto.request.LessonRequest;
import com.chinese_dictation.model.dto.response.DataPagedResponse;
import com.chinese_dictation.model.dto.response.LessonResponse;
import com.chinese_dictation.model.enums.VocabularyLevel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ILessonService {
    LessonResponse getLessonById(Long id);
    LessonResponse createLesson(LessonRequest request, MultipartFile fileAudio);
    LessonResponse updateLesson(Long id, LessonRequest request);
    void deleteLesson(Long id);
    List<LessonResponse> getLessonByCategoryAndLevel(Long categoryId, VocabularyLevel level);
    DataPagedResponse<LessonResponse> getLessonPaged(int size, int page);
}
