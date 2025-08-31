package com.chinese_dictation.service.iplm;

import com.chinese_dictation.mapper.LessonMapper;
import com.chinese_dictation.mapper.SentenceMapper;
import com.chinese_dictation.model.dto.request.LessonRequest;
import com.chinese_dictation.model.dto.response.LessonResponse;
import com.chinese_dictation.model.entity.Category;
import com.chinese_dictation.model.entity.Lesson;
import com.chinese_dictation.model.enums.VocabularyLevel;
import com.chinese_dictation.repository.CategoryRepository;
import com.chinese_dictation.repository.LessonRepository;
import com.chinese_dictation.service.ILessonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService implements ILessonService {
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final SentenceMapper sentenceMapper;
    private final CloudinaryService cloudinaryService;
    private final CategoryRepository categoryRepository;

    @Override
    public LessonResponse getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));

        return lessonMapper.toLessonResponse(lesson);
    }

    @Transactional
    @Override
    public LessonResponse createLesson(LessonRequest request, MultipartFile fileAudio) {
        String audioFilePath;
        try {
            audioFilePath = cloudinaryService.uploadAudio(fileAudio);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not exist"));

        Lesson lesson = lessonMapper.toLesson(request);
        lesson.setAudioFilePath(audioFilePath);
        lesson.setCategory(category);
        Lesson savedLesson = lessonRepository.save(lesson);
        return lessonMapper.toLessonResponse(lesson);
    }

    @Transactional
    @Override
    public LessonResponse updateLesson(Long id, LessonRequest request) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));

        lesson.setTitleChinese(request.titleChinese());
        lesson.setTitleVietnamese(request.titleVietnamese());
        lesson.setDescription(request.description());
        lesson.setDisplayOrder(request.displayOrder());
        lesson.setLevel(request.level());
        lesson.setTotalSentences(request.totalSentences());
        lesson.setEstimatedDurationSeconds(request.estimatedDurationSeconds());

        return lessonMapper.toLessonResponse(lesson);
    }

    @Override
    public void deleteLesson(Long id) {
        Lesson lesson = lessonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));

        lessonRepository.delete(lesson);
    }

    @Override
    public List<LessonResponse> getLessonByCategoryAndLevel(Long categoryId, VocabularyLevel level) {
        return lessonRepository.findAllCategoryAndLevel(categoryId, level).stream()
                .map(lessonMapper::toLessonResponse)
                .toList();
    }
}
