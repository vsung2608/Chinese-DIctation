package com.chinese_dictation.controller.user;

import com.chinese_dictation.model.dto.request.ChangePasswordRequest;
import com.chinese_dictation.model.dto.request.UserRequest;
import com.chinese_dictation.model.dto.response.UserResponse;
import com.chinese_dictation.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserProfileController {
    private final IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getProfile(@PathVariable("id") Long id){
        return ResponseEntity.ok(userService.getProfile(id));
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateProfile(@RequestBody UserRequest request){
        return ResponseEntity.ok(userService.updateProfile(request));
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request){
        userService.changePassword(request);
        return ResponseEntity.ok("Cập nhật mật khẩu thành công");
    }

    @PostMapping("/change-email")
    public ResponseEntity<String> changeEmail(@RequestParam("id") Long id, @RequestParam("email") String email){
        userService.changeEmail(id, email);
        return ResponseEntity.ok("Cập nhật email thành công");
    }
}