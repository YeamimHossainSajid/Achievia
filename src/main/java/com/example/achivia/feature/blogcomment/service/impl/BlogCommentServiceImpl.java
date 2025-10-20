package com.example.achivia.feature.blogcomment.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.blog.entity.Blog;
import com.example.achivia.feature.blog.repository.BlogRepository;
import com.example.achivia.feature.blogcomment.entity.BlogComment;
import com.example.achivia.feature.blogcomment.payload.request.BlogCommentRequestDto;
import com.example.achivia.feature.blogcomment.payload.response.BlogCommentResponseDto;
import com.example.achivia.feature.blogcomment.repository.BlogCommentRepository;
import com.example.achivia.feature.blogcomment.service.BlogCommentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlogCommentServiceImpl implements BlogCommentService {

    private final BlogCommentRepository commentRepository;
    private final BlogRepository blogRepository;
    private final UserRepo userRepository;

    @Override
    public BlogCommentResponseDto createComment(BlogCommentRequestDto requestDto) {
        Blog blog = blogRepository.findById(requestDto.getBlogId())
                .orElseThrow(() -> new EntityNotFoundException("Blog not found"));

        User user = userRepository.findById(requestDto.getUserId()).get();

        BlogComment parentComment = null;
        if (requestDto.getParentCommentId() != null) {
            parentComment = commentRepository.findById(requestDto.getParentCommentId())
                    .orElseThrow(() -> new EntityNotFoundException("Parent comment not found"));
        }

        BlogComment comment = BlogComment.builder()
                .blog(blog)
                .user(user)
                .content(requestDto.getContent())
                .parentComment(parentComment)
                .build();

        BlogComment saved = commentRepository.save(comment);
        return convertToResponseDto(saved);
    }

    @Override
    public List<BlogCommentResponseDto> getCommentsByBlogId(UUID blogId) {
        List<BlogComment> topLevelComments = commentRepository.findTopLevelComments(blogId);
        return topLevelComments.stream()
                .map(this::buildCommentTree)
                .collect(Collectors.toList());
    }

    private BlogCommentResponseDto buildCommentTree(BlogComment comment) {
        List<BlogComment> replies = commentRepository.findRepliesByParentId(comment.getId());

        List<BlogCommentResponseDto> replyDtos = replies.stream()
                .map(this::buildCommentTree)
                .collect(Collectors.toList());

        BlogCommentResponseDto dto = convertToResponseDto(comment);
        dto.setReplies(replyDtos);
        return dto;
    }

    private BlogCommentResponseDto convertToResponseDto(BlogComment comment) {
        return BlogCommentResponseDto.builder()
                .id(comment.getId())
                .blogId(comment.getBlog().getId())
                .userId(comment.getUser().getId())
                .userName(comment.getUser().getUsername())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .parentCommentId(comment.getParentComment() != null ? comment.getParentComment().getId() : null)
                .build();
    }
}
