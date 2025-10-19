package com.example.achivia.feature.blogvote.payload.request;

import com.example.achivia.feature.blogvote.entity.BlogVote.VoteType;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogVoteRequestDto {
    private UUID blogId;
    private UUID userId;
    private VoteType voteType;
}
