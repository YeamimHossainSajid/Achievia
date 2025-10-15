package com.example.achivia.feature.userstats.payload.request;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStatsRequestDto {
    private UUID userId;
    private Long totalPoints;
    private Integer totalSubmissions;
    private Integer solvedProblems;
    private Long competitionPoints;
    private Long habitPoints;
}
