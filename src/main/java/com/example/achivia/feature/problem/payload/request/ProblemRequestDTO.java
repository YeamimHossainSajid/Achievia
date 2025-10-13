package com.example.achivia.feature.problem.payload.request;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemRequestDTO {
    private String slug;
    private String title;
    private String difficulty;
    private String statement;
    private String constraints;
    private String inputFormat;
    private String outputFormat;
    private UUID creatorUserId;
    private Boolean isPublished;
}
