package com.example.achivia.feature.blog.payload.response;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogResponseDto {
    private UUID id;
    private UUID authorId;
    private String title;
    private String slug;
    private String content;
    private String coverImageUrl;
    private boolean isPublished;
    private Instant createdAt;
    private Instant updatedAt;
}

