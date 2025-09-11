package com.chinese_dictation.service.iplm;

import com.chinese_dictation.mapper.CategoryMapper;
import com.chinese_dictation.model.dto.response.CategoryResponse;
import com.chinese_dictation.model.dto.response.CategoryStatisticsRespoonse;
import com.chinese_dictation.repository.CategoryRepository;
import com.chinese_dictation.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getAllCategory() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }

    @Override
    public List<CategoryStatisticsRespoonse> getCategoryStatistics() {
        return categoryRepository.findCategoryStatistics();
    }
}
