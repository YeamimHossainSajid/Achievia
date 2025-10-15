package com.example.achivia.feature.score.payload.request;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreEventRequestDto {
    private UUID userId;
    private String source;
    private UUID sourceId;
    private Integer points;
    private String metadata;
}
