package com.chinese_dictation.mapper;

import com.chinese_dictation.model.dto.response.CategoryResponse;
import com.chinese_dictation.model.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryResponse toCategoryResponse(Category category) {
        return new CategoryResponse(category.getId(), category.getName(), category.getImageUrl(), category.getDescription());
    }
}
