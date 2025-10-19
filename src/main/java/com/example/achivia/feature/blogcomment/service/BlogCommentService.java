package com.example.achivia.feature.blogcomment.service;

import com.example.achivia.feature.blogcomment.payload.request.BlogCommentRequestDto;
import com.example.achivia.feature.blogcomment.payload.response.BlogCommentResponseDto;

import java.util.List;
import java.util.UUID;

public interface BlogCommentService {
    BlogCommentResponseDto createComment(BlogCommentRequestDto requestDto);
    List<BlogCommentResponseDto> getCommentsByBlogId(UUID blogId);
}
