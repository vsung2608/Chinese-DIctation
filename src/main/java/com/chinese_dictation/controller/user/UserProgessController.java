package com.chinese_dictation.controller.user;

import com.chinese_dictation.model.dto.request.UserProgressRequest;
import com.chinese_dictation.model.dto.response.UserProgressResponse;
import com.chinese_dictation.model.entity.Users;
import com.chinese_dictation.service.iplm.UserProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/progress")
public class UserProgessController {
    private final UserProgressService userProgressService;

    @GetMapping("/by-lesson/{id}")
    public ResponseEntity<UserProgressResponse> byLesson(
            @AuthenticationPrincipal Users user,
            @PathVariable Long id
    ){
        return ResponseEntity.ok(userProgressService.getUserProgress(user.getId(), id));
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<String> completeLesosn(
            @PathVariable Long id,
            @RequestParam Long timeCompleted){
        userProgressService.completeLesson(id, timeCompleted);
        return ResponseEntity.ok("Completed");
    }

    @PostMapping
    public ResponseEntity<String> createUserProgress(
            @AuthenticationPrincipal Users user,
            @RequestBody UserProgressRequest request
            ){
        userProgressService.createUserProgress(user.getId(), request);
        return ResponseEntity.ok("Đã lưu tiến trình học của người dùng");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUserProgress(
            @PathVariable("id") Long progressId,
            @RequestBody UserProgressRequest request
    ){
        userProgressService.updateUserProgress(request, progressId);
        return ResponseEntity.ok("Đã cập nhật tiến trình học của người dùng");
    }
}
