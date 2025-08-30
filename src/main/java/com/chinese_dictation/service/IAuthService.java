package com.chinese_dictation.service;

import com.chinese_dictation.model.dto.request.LoginRequest;
import com.chinese_dictation.model.dto.request.RegistrationRequest;
import com.chinese_dictation.model.dto.response.AuthResponse;
import com.chinese_dictation.model.dto.response.UserResponse;
import com.chinese_dictation.model.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface IAuthService {
    UserResponse register(RegistrationRequest request);

    AuthResponse authenticate(LoginRequest request);

    void activationAccount(String emailToken);

    void sendValidationEmail(Users user);

    String generateAndSaveEmailToken(Users user);

    String generateActivationCode(int length);
}
