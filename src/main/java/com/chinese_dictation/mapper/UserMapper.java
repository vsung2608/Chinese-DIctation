package com.chinese_dictation.mapper;

import com.chinese_dictation.model.dto.request.RegistrationRequest;
import com.chinese_dictation.model.dto.response.UserResponse;
import com.chinese_dictation.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getTotalScore(),
                user.getLessonsCompleted(),
                user.getCreatedAt(),
                user.getLastLogin(),
                user.getIsActive()
        );
    }

    public User toUser(RegistrationRequest request) {
        return User.builder()
                .username(request.username())
                .fullName(request.fullName())
                .build();
    }
}
