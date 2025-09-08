package com.chinese_dictation.controller.admin;

import com.chinese_dictation.model.dto.response.DataPagedResponse;
import com.chinese_dictation.model.dto.response.UserResponse;
import com.chinese_dictation.service.iplm.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/profiles")
@RequiredArgsConstructor
public class AdminProfileController {

    private final UserService userService;

    @GetMapping
    public DataPagedResponse<UserResponse> getProfiles(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size){
        return userService.getAllUsers(page, size);
    }

    @PatchMapping("/block/{id}")
    public ResponseEntity<String> blockUser(@PathVariable Long id){
        userService.blockUser(id);
        return ResponseEntity.ok("User was blocked");
    }
}
