package com.example.achivia.feature.blog.service;

import com.example.achivia.feature.blog.payload.request.BlogRequestDto;
import com.example.achivia.feature.blog.payload.response.BlogResponseDto;

import java.util.List;
import java.util.UUID;

public interface BlogService {
    BlogResponseDto createBlog(BlogRequestDto requestDto);
    BlogResponseDto updateBlog(UUID id, BlogRequestDto requestDto);
    List<BlogResponseDto> getBlogsByAuthor(Long authorId);
    BlogResponseDto getBlogById(UUID id);
    BlogResponseDto getBlogBySlug(String slug);
    void deleteBlog(UUID id);
}

