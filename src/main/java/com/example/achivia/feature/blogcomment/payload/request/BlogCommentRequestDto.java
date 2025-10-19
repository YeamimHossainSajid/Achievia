package com.example.achivia.feature.blogcomment.payload.request;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlogCommentRequestDto {
    private UUID blogId;
    private UUID userId;
    private String content;
    private UUID parentCommentId;
}

