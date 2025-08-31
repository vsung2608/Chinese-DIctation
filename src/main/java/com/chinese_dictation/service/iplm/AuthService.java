package com.chinese_dictation.service.iplm;

import com.chinese_dictation.email.EmailService;
import com.chinese_dictation.exception.BusinessError;
import com.chinese_dictation.exception.BusinessException;
import com.chinese_dictation.mapper.UserMapper;
import com.chinese_dictation.model.dto.request.LoginRequest;
import com.chinese_dictation.model.dto.request.RefreshTokenRequest;
import com.chinese_dictation.model.dto.request.RegistrationRequest;
import com.chinese_dictation.model.dto.response.AuthResponse;
import com.chinese_dictation.model.dto.response.UserResponse;
import com.chinese_dictation.model.entity.Role;
import com.chinese_dictation.model.entity.Token;
import com.chinese_dictation.model.entity.Users;
import com.chinese_dictation.repository.RoleRepository;
import com.chinese_dictation.repository.TokenRepository;
import com.chinese_dictation.repository.UserRepository;
import com.chinese_dictation.security.JwtService;
import com.chinese_dictation.service.IAuthService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final RoleRepository roleRepository;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse register(RegistrationRequest request){
        Users user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        Role role = roleRepository.findByName(com.chinese_dictation.model.enums.Role.USER)
                .orElseThrow(() -> new EntityNotFoundException("Role USER not found"));

        user.setRoles(new ArrayList<>(Collections.singleton(role)));

        try {
            userRepository.save(user);
        }catch (Exception e){
            throw new BusinessException(BusinessError.EMAIL_EXISTED);
        }
        sendValidationEmail(user);
        return userMapper.toUserResponse(user);
    }

    @Override
    public AuthResponse authenticate(LoginRequest request){
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.username(),
                            request.password())
            );
        }catch (Exception e){
            log.error(e.getMessage());
            throw new BusinessException(BusinessError.INVALID_EMAIL_PASSWORD);
        }

        var user = (Users) auth.getPrincipal();

        List<String> roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("fullname", user.getFullName());
        claims.put("roles", roles);
        claims.put("tokenId", UUID.randomUUID().toString());
        var token = jwtService.generateToken(claims, user);
        return new AuthResponse(token);
    }

    @Override
    public void logout(String token) {
        jwtService.addTokenToBlackList(token);
    }

    @Override
    public AuthResponse refresh(RefreshTokenRequest refreshToken) {
        Users userDetail = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(jwtService.isTokenValid(refreshToken.token(), userDetail)){
            throw new BusinessException(BusinessError.INVALID_TOKEN);
        }

        if(jwtService.isTokenInBlackList(refreshToken.token())){
            throw new BusinessException(BusinessError.INVALID_TOKEN);
        }

        List<String> roles = userDetail.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("fullname", userDetail.getFullName());
        claims.put("roles", roles);
        claims.put("tokenId", UUID.randomUUID().toString());
        var token = jwtService.generateToken(claims, userDetail);
        return new AuthResponse(token);
    }

    @Override
    public void activationAccount(String token) {
        var emailTokenSaved = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Email token not found"));

        if(LocalDateTime.now().isAfter(emailTokenSaved.getExpiresAt())){
            sendValidationEmail(emailTokenSaved.getUser());
            throw new RuntimeException("Email token expired. A new email token has been created, please check your email.");
        }

        var user = userRepository.findById(emailTokenSaved.getUser().getId())
                .orElseThrow(() -> new RuntimeException("Users not found"));

        user.setEnabled(true);
        userRepository.save(user);
        emailTokenSaved.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(emailTokenSaved);
    }

    @Override
    public void sendValidationEmail(Users user) {
        var activationCode = generateAndSaveEmailToken(user);

        emailService.sendActivationEmail(activationCode, user.getFullName(), user.getUsername());
    }

    @Override
    public String generateAndSaveEmailToken(Users user){
        var activationCode = generateActivationCode(6);

        var emailToken = Token.builder()
                .token(activationCode)
                .createdAt(LocalDateTime.from(LocalDateTime.now()))
                .expiresAt(LocalDateTime.from(LocalDateTime.now().plusMinutes(15)))
                .user(user)
                .build();

        tokenRepository.save(emailToken);
        return emailToken.getToken();
    }

    @Override
    public String generateActivationCode(int length){
        String characters = "0123456789";
        StringBuilder code = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int charAt = random.nextInt(characters.length());
            code.append(characters.charAt(charAt));
        }
        return code.toString();
    }
}
