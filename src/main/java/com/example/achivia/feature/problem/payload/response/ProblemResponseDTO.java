package com.example.achivia.feature.problem.payload.response;

import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemResponseDTO {
    private UUID id;
    private String slug;
    private String title;
    private String difficulty;
    private String statement;
    private String constraints;
    private String inputFormat;
    private String outputFormat;
    private UUID creatorUserId;
    private Boolean isPublished;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
