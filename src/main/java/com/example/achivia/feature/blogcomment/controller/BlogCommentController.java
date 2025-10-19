package com.example.achivia.feature.blogcomment.controller;
import com.example.achivia.feature.blogcomment.payload.request.BlogCommentRequestDto;
import com.example.achivia.feature.blogcomment.payload.response.BlogCommentResponseDto;
import com.example.achivia.feature.blogcomment.service.BlogCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/blogs/{blogId}/comments")
@RequiredArgsConstructor
public class BlogCommentController {

    private final BlogCommentService commentService;

    @PostMapping
    public ResponseEntity<BlogCommentResponseDto> createComment(
            @PathVariable UUID blogId,
            @RequestBody BlogCommentRequestDto requestDto
    ) {
        requestDto.setBlogId(blogId);
        return ResponseEntity.ok(commentService.createComment(requestDto));
    }

    @GetMapping
    public ResponseEntity<List<BlogCommentResponseDto>> getComments(
            @PathVariable UUID blogId
    ) {
        return ResponseEntity.ok(commentService.getCommentsByBlogId(blogId));
    }
}
