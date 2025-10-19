package com.example.achivia.feature.blogcomment.payload.response;

import lombok.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogCommentResponseDto {
    private UUID id;
    private UUID blogId;
    private UUID userId;
    private String userName;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;
    private UUID parentCommentId;
    private List<BlogCommentResponseDto> replies;
}

