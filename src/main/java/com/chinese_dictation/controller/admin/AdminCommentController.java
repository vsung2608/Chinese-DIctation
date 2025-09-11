package com.chinese_dictation.controller.admin;

import com.chinese_dictation.model.dto.response.CommentResponse;
import com.chinese_dictation.model.dto.response.DataPagedResponse;
import com.chinese_dictation.service.iplm.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/comments")
public class AdminCommentController {
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<DataPagedResponse<CommentResponse>> getComments(
            @RequestParam(value = "page", defaultValue = "1", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(this.commentService.getCommentOrderByDate(page, size));
    }
}
