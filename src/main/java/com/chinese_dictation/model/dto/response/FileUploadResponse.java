package com.chinese_dictation.model.dto.response;

public record FileUploadResponse(
        String secureUrl,
        String publicId
) {
}
