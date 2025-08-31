package com.chinese_dictation.service;

import com.chinese_dictation.model.dto.request.LoginRequest;
import com.chinese_dictation.model.dto.request.RefreshTokenRequest;
import com.chinese_dictation.model.dto.request.RegistrationRequest;
import com.chinese_dictation.model.dto.response.AuthResponse;
import com.chinese_dictation.model.dto.response.UserResponse;
import com.chinese_dictation.model.entity.Users;

public interface IAuthService {
    UserResponse register(RegistrationRequest request);

    AuthResponse authenticate(LoginRequest request);

    void logout(String token);

    AuthResponse refresh(RefreshTokenRequest refreshToken);

    void activationAccount(String emailToken);

    void sendValidationEmail(Users user);

    String generateAndSaveEmailToken(Users user);

    String generateActivationCode(int length);
}
