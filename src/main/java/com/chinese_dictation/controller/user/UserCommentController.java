package com.chinese_dictation.controller.user;

import com.chinese_dictation.model.dto.request.NewCommentRequest;
import com.chinese_dictation.model.dto.request.UpdateCommentRequest;
import com.chinese_dictation.model.dto.response.CommentResponse;
import com.chinese_dictation.service.iplm.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user/comments")
@RequiredArgsConstructor
public class UserCommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> createComment(
            @RequestPart("comment") NewCommentRequest request,
            @RequestPart(value = "image", required = false) MultipartFile attachImage
            ){
        return ResponseEntity.ok(commentService.createComment(attachImage, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable("id") Long id, @RequestBody UpdateCommentRequest request){
        return ResponseEntity.ok(commentService.updateComment(id, request));
    }
}
