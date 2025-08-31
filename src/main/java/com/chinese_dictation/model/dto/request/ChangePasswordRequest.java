package com.chinese_dictation.model.dto.request;

public record ChangePasswordRequest(
        Long id,
        String oldPassword,
        String newPassword
) {
}
