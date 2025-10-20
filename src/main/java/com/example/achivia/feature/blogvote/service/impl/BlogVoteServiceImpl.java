package com.example.achivia.feature.blogvote.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.blog.entity.Blog;
import com.example.achivia.feature.blog.repository.BlogRepository;
import com.example.achivia.feature.blogvote.entity.BlogVote;
import com.example.achivia.feature.blogvote.payload.request.BlogVoteRequestDto;
import com.example.achivia.feature.blogvote.payload.response.BlogVoteResponseDto;
import com.example.achivia.feature.blogvote.repository.BlogVoteRepository;
import com.example.achivia.feature.blogvote.service.BlogVoteService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlogVoteServiceImpl implements BlogVoteService {

    private final BlogVoteRepository voteRepository;
    private final BlogRepository blogRepository;
    private final UserRepo userRepository;

    @Override
    @Transactional
    public BlogVoteResponseDto castVote(BlogVoteRequestDto requestDto) {
        Blog blog = blogRepository.findById(requestDto.getBlogId())
                .orElseThrow(() -> new EntityNotFoundException("Blog not found"));

        User user = userRepository.findById(requestDto.getUserId()).get();

        BlogVote vote = voteRepository.findByBlogIdAndUserId(requestDto.getBlogId(), requestDto.getUserId())
                .orElse(BlogVote.builder()
                        .blog(blog)
                        .user(user)
                        .build());

        vote.setVoteType(requestDto.getVoteType());

        BlogVote saved = voteRepository.save(vote);

        return convertToResponseDto(saved);
    }

    @Override
    public long countUpvotes(UUID blogId) {
        return voteRepository.countUpvotes(blogId);
    }

    @Override
    public long countDownvotes(UUID blogId) {
        return voteRepository.countDownvotes(blogId);
    }

    private BlogVoteResponseDto convertToResponseDto(BlogVote vote) {
        return BlogVoteResponseDto.builder()
                .id(vote.getId())
                .blogId(vote.getBlog().getId())
                .userId(vote.getUser().getId())
                .userName(vote.getUser().getUsername())
                .voteType(vote.getVoteType())
                .createdAt(vote.getCreatedAt())
                .build();
    }
}
