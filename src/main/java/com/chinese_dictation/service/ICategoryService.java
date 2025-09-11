package com.chinese_dictation.service;

import com.chinese_dictation.model.dto.response.CategoryResponse;
import com.chinese_dictation.model.dto.response.CategoryStatisticsRespoonse;

import java.util.List;

public interface ICategoryService {
    List<CategoryResponse> getAllCategory();

    List<CategoryStatisticsRespoonse> getCategoryStatistics();
}
