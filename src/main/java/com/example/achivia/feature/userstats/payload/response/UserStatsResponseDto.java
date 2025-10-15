package com.example.achivia.feature.userstats.payload.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStatsResponseDto {
    private UUID userId;
    private Long totalPoints;
    private Integer totalSubmissions;
    private Integer solvedProblems;
    private Long competitionPoints;
    private Long habitPoints;
    private LocalDateTime lastRecalculatedAt;
}
