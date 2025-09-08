package com.chinese_dictation.mapper;

import com.chinese_dictation.model.dto.request.RegistrationRequest;
import com.chinese_dictation.model.dto.request.UserRequest;
import com.chinese_dictation.model.dto.response.UserResponse;
import com.chinese_dictation.model.entity.Users;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toUserResponse(Users user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getCreatedAt(),
                user.getLastLogin(),
                user.getAvatarUrl(),
                user.getStatus().name(),
                user.getCountryName(),
                user.getCountryCode()
        );
    }

    public Users toUser(RegistrationRequest request) {
        return Users.builder()
                .username(request.username())
                .fullName(request.fullName())
                .isActive(true)
                .build();
    }
}
