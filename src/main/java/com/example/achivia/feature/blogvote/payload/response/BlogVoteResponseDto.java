package com.example.achivia.feature.blogvote.payload.response;

import com.example.achivia.feature.blogvote.entity.BlogVote.VoteType;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogVoteResponseDto {
    private UUID id;
    private UUID blogId;
    private UUID userId;
    private String userName;
    private VoteType voteType;
    private Instant createdAt;
}
