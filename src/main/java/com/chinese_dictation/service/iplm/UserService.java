package com.chinese_dictation.service.iplm;

import com.chinese_dictation.exception.BusinessError;
import com.chinese_dictation.exception.BusinessException;
import com.chinese_dictation.mapper.UserMapper;
import com.chinese_dictation.model.dto.request.ChangePasswordRequest;
import com.chinese_dictation.model.dto.request.UserRequest;
import com.chinese_dictation.model.dto.response.UserResponse;
import com.chinese_dictation.model.entity.Users;
import com.chinese_dictation.repository.UserRepository;
import com.chinese_dictation.service.IUserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse getProfile(Long id) {
        Users user = findUserById(id);
        return userMapper.toUserResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    @Override
    public UserResponse updateProfile(UserRequest request) {
        Users user = findUserById(request.id());

        user.setFullName(request.fullName());
        user.setAvatarUrl(request.avatarUrl());
        return null;
    }

    @Override
    public void changePassword(ChangePasswordRequest request) {
        Users user = findUserById(request.id());

        if(!passwordEncoder.matches(request.oldPassword(), user.getPassword())){
            throw new BusinessException(BusinessError.INCCORECT_OLDPASSWORLD);
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);
    }

    @Override
    public void changeEmail(Long id, String email) {
        Users user = findUserById(id);
        userRepository.findByUsername(email)
                .orElseThrow(() -> new BusinessException(BusinessError.EMAIL_EXISTED));
        user.setUsername(email);
        userRepository.save(user);
    }

    private Users findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy thông tin người dùng với ID::%s".formatted(id.toString())));
    }
}
