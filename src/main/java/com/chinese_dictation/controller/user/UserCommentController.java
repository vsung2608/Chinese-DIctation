package com.chinese_dictation.controller.user;

import com.chinese_dictation.model.dto.request.NewCommentRequest;
import com.chinese_dictation.model.dto.request.UpdateCommentRequest;
import com.chinese_dictation.model.dto.response.CommentResponse;
import com.chinese_dictation.model.dto.response.DataPagedResponse;
import com.chinese_dictation.service.iplm.CommentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
            @RequestPart("comment") String commentJson,
            @RequestPart(value = "image", required = false) MultipartFile attachImage
            ) throws JsonProcessingException {
        NewCommentRequest request = new ObjectMapper().readValue(commentJson, NewCommentRequest.class);
        return ResponseEntity.ok(commentService.createComment(attachImage, request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable("id") Long id, @RequestBody UpdateCommentRequest request){
        return ResponseEntity.ok(commentService.updateComment(id, request));
    }

    @GetMapping("/by-lesson")
    public ResponseEntity<DataPagedResponse<CommentResponse>> getComments(
            @RequestParam("lessonId") Long lessonId,
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(commentService.getComment(lessonId, page, size));
    }

    @GetMapping("/replies")
    public ResponseEntity<DataPagedResponse<CommentResponse>> getReplyComments(
            @RequestParam("parentCommentId") Long parentCommentId,
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ){
        return ResponseEntity.ok(commentService.getReplyComment(parentCommentId, page, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return ResponseEntity.ok("Comment deleted.");
    }
}
