package com.example.achivia.feature.blogvote.controller;

import com.example.achivia.feature.blogvote.payload.request.BlogVoteRequestDto;
import com.example.achivia.feature.blogvote.payload.response.BlogVoteResponseDto;
import com.example.achivia.feature.blogvote.service.BlogVoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/blogs/{blogId}/votes")
@RequiredArgsConstructor
public class BlogVoteController {

    private final BlogVoteService blogVoteService;

    @PostMapping
    public ResponseEntity<BlogVoteResponseDto> castVote(
            @PathVariable UUID blogId,
            @RequestBody BlogVoteRequestDto requestDto
    ) {
        requestDto.setBlogId(blogId);
        return ResponseEntity.ok(blogVoteService.castVote(requestDto));
    }

    @GetMapping("/count")
    public ResponseEntity<Map<String, Long>> getVoteCounts(@PathVariable UUID blogId) {
        long upvotes = blogVoteService.countUpvotes(blogId);
        long downvotes = blogVoteService.countDownvotes(blogId);
        return ResponseEntity.ok(Map.of("upvotes", upvotes, "downvotes", downvotes));
    }
}
