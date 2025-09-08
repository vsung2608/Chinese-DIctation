package com.chinese_dictation.service.iplm;

import com.chinese_dictation.mapper.LessonMapper;
import com.chinese_dictation.mapper.SentenceMapper;
import com.chinese_dictation.model.dto.request.LessonRequest;
import com.chinese_dictation.model.dto.response.DataPagedResponse;
import com.chinese_dictation.model.dto.response.FileUploadResponse;
import com.chinese_dictation.model.dto.response.LessonResponse;
import com.chinese_dictation.model.entity.Category;
import com.chinese_dictation.model.entity.Lesson;
import com.chinese_dictation.model.entity.Sentence;
import com.chinese_dictation.model.enums.VocabularyLevel;
import com.chinese_dictation.repository.CategoryRepository;
import com.chinese_dictation.repository.LessonRepository;
import com.chinese_dictation.repository.SentenceRepository;
import com.chinese_dictation.service.ILessonService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class LessonService implements ILessonService {
    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;
    private final SentenceMapper sentenceMapper;
    private final CloudinaryService cloudinaryService;
    private final CategoryRepository categoryRepository;
    private final SentenceRepository sentenceRepository;

    @Override
    public LessonResponse getLessonById(Long id) {
        Lesson lesson = lessonRepository.findWithSentencesById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lesson not found"));

        return lessonMapper.toLessonWithSentencesResponse(lesson);
    }

    @Transactional
    @Override
    public LessonResponse createLesson(LessonRequest request, MultipartFile fileAudio) {
        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category not exist"));

        FileUploadResponse fileUploadResponse;
        try {
            fileUploadResponse = cloudinaryService.uploadAudio(fileAudio);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        try {
            Lesson lesson = lessonMapper.toLesson(request);
            lesson.setAudioFilePath(fileUploadResponse.secureUrl());
            lesson.setCategory(category);
            Lesson savedLesson = lessonRepository.save(lesson);

            List<Sentence> sentences = request.sentences().stream()
                    .map(sentenceMapper::toEntity)
                    .peek(sentence -> sentence.setLesson(savedLesson))
                    .toList();

            sentenceRepository.saveAll(sentences);
            lesson.setSentences(sentences);
            return lessonMapper.toLessonResponse(lesson);
        }catch (Exception e){
            try {
                cloudinaryService.deleteFile(fileUploadResponse.publicId(), "video");
            }catch (Exception cleanupException){
                log.warn("Failed to cleanup uploaded file: {}", fileUploadResponse.secureUrl(), cleanupException);
            }
            throw new RuntimeException(e);
        }
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
    public List<LessonResponse> getLessonByCategoryAndLevel(Long categoryId, VocabularyLevel level, Long userId) {
        return lessonRepository.findAllCategoryAndLevel(categoryId, level, userId).stream()
                .map(lessonMapper::toLessonResponse)
                .toList();
    }

    @Override
    public DataPagedResponse<LessonResponse> getLessonPaged(int size, int page) {
        Pageable pageable = PageRequest.of(page-1, size);
        Page<Lesson> lessons = lessonRepository.findAll(pageable);
        return new DataPagedResponse<>(
                page,
                lessons.getTotalPages(),
                lessons.getSize(),
                lessons.getTotalElements(),
                lessons.getContent().stream()
                        .map(lessonMapper::toLessonResponse)
                        .toList()
        );
    }
}
