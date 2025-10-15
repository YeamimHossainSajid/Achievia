package com.example.achivia.feature.submission.payload.request;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionRequestDto {
    private UUID userId;
    private UUID problemId;
    private UUID competitionId;
    private String language;
    private String code;
    private String status;
    private Integer score;
    private Integer timeMs;
    private Integer memoryKb;
    private String errorMessage;
}
