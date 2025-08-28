package com.chinese_dictation.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RegistrationRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String username,
        @Min(value = 8, message = "Password must be at least 8 characters")
        String password,
        @NotBlank(message = "FullName is required")
        String fullName
) {
}
