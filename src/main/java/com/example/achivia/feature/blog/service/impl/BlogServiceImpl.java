package com.example.achivia.feature.blog.service.impl;

import com.example.achivia.auth.model.User;

import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.blog.entity.Blog;
import com.example.achivia.feature.blog.payload.request.BlogRequestDto;
import com.example.achivia.feature.blog.payload.response.BlogResponseDto;
import com.example.achivia.feature.blog.repository.BlogRepository;
import com.example.achivia.feature.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;
    private final UserRepo userRepository;

    @Override
    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        User author = userRepository.findById(requestDto.getAuthorId());

        Blog blog = Blog.builder()
                .author(author)
                .title(requestDto.getTitle())
                .slug(requestDto.getSlug())
                .content(requestDto.getContent())
                .coverImageUrl(requestDto.getCoverImageUrl())
                .isPublished(requestDto.isPublished())
                .build();

        blogRepository.save(blog);
        return mapToDto(blog);
    }

    @Override
    public BlogResponseDto updateBlog(UUID id, BlogRequestDto requestDto) {
        Blog blog = blogRepository.findByIdNative(id);
        if (blog == null) throw new RuntimeException("Blog not found");

        blog.setTitle(requestDto.getTitle());
        blog.setSlug(requestDto.getSlug());
        blog.setContent(requestDto.getContent());
        blog.setCoverImageUrl(requestDto.getCoverImageUrl());
        blog.setPublished(requestDto.isPublished());

        blogRepository.save(blog);
        return mapToDto(blog);
    }

    @Override
    public List<BlogResponseDto> getBlogsByAuthor(Long authorId) {
        List<Blog> blogs = blogRepository.findByAuthorIdNative(authorId);
        return blogs.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public BlogResponseDto getBlogById(UUID id) {
        Blog blog = blogRepository.findByIdNative(id);
        if (blog == null) throw new RuntimeException("Blog not found");
        return mapToDto(blog);
    }

    @Override
    public BlogResponseDto getBlogBySlug(String slug) {
        Blog blog = blogRepository.findBySlugNative(slug);
        if (blog == null) throw new RuntimeException("Blog not found");
        return mapToDto(blog);
    }

    @Override
    public void deleteBlog(UUID id) {
        Blog blog = blogRepository.findByIdNative(id);
        if (blog == null) throw new RuntimeException("Blog not found");
        blogRepository.delete(blog);
    }

    private BlogResponseDto mapToDto(Blog blog) {
        return BlogResponseDto.builder()
                .id(blog.getId())
                .authorId(blog.getAuthor().getId())
                .title(blog.getTitle())
                .slug(blog.getSlug())
                .content(blog.getContent())
                .coverImageUrl(blog.getCoverImageUrl())
                .isPublished(blog.isPublished())
                .createdAt(blog.getCreatedAt())
                .updatedAt(blog.getUpdatedAt())
                .build();
    }
}
