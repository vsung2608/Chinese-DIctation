package com.chinese_dictation.service;

import com.chinese_dictation.model.dto.request.ChangePasswordRequest;
import com.chinese_dictation.model.dto.request.UserRequest;
import com.chinese_dictation.model.dto.response.DataPagedResponse;
import com.chinese_dictation.model.dto.response.UserResponse;

public interface IUserService {
    UserResponse getProfile(Long id);
    DataPagedResponse<UserResponse> getAllUsers(int page, int size);
    UserResponse updateProfile(UserRequest request);
    void changePassword(ChangePasswordRequest request);
    void changeEmail(Long id, String email);
    void blockUser(Long id);
    void unblockUser(Long id);
}
