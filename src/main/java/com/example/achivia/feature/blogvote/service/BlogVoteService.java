package com.example.achivia.feature.blogvote.service;

import com.example.achivia.feature.blogvote.payload.request.BlogVoteRequestDto;
import com.example.achivia.feature.blogvote.payload.response.BlogVoteResponseDto;

import java.util.UUID;

public interface BlogVoteService {
    BlogVoteResponseDto castVote(BlogVoteRequestDto requestDto);
    long countUpvotes(UUID blogId);
    long countDownvotes(UUID blogId);
}
