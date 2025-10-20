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
        UUID id = UUID.randomUUID();

        int rowsInserted = commentRepository.saveIfAllExist(
                id,
                requestDto.getBlogId(),
                requestDto.getUserId(),
                requestDto.getContent(),
                requestDto.getParentCommentId()
        );

        if (rowsInserted == 0) {
            throw new RuntimeException("Invalid blog ID, user ID, or parent comment ID");
        }

        BlogComment comment = BlogComment.builder()
                .id(id)
                .content(requestDto.getContent())
                .build();

        return convertToResponseDto(comment);
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
