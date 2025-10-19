package com.example.achivia.feature.blog.payload.request;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogRequestDto {
    private UUID authorId;
    private String title;
    private String slug;
    private String content;
    private String coverImageUrl;
    private boolean isPublished;
}

