package com.example.achivia.feature.score.payload.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreEventResponseDto {
    private UUID id;
    private UUID userId;
    private String source;
    private UUID sourceId;
    private Integer points;
    private String metadata;
    private LocalDateTime occurredAt;
    private LocalDateTime createdAt;
}
