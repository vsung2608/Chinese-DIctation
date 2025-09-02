package com.chinese_dictation.model.dto.response;

import java.util.List;

public record DataPagedResponse<T>(
        int currentPage,
        int totalPages,
        int pageSize,
        long totalElements,
        List<T> data
) {
}
