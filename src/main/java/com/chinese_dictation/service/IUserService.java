package com.chinese_dictation.service;

import com.chinese_dictation.model.dto.request.ChangePasswordRequest;
import com.chinese_dictation.model.dto.request.UserRequest;
import com.chinese_dictation.model.dto.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService {
    UserResponse getProfile(Long id);
    List<UserResponse> getAllUsers();
    UserResponse updateProfile(UserRequest request);
    void changePassword(ChangePasswordRequest request);
    void changeEmail(Long id, String email);
}
