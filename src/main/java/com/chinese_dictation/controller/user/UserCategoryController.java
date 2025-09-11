package com.chinese_dictation.controller.user;

import com.chinese_dictation.model.dto.response.CategoryStatisticsRespoonse;
import com.chinese_dictation.service.iplm.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/categories")
public class UserCategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryStatisticsRespoonse>> findAll() {
        return ResponseEntity.ok(categoryService.getCategoryStatistics());
    }
}
