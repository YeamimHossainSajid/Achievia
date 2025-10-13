package com.example.achivia.feature.competitionperticipent.payload.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class CompetitionParticipantResponseDTO {
    private UUID competitionId;
    private UUID userId;
    private Integer totalScore;
    private LocalDateTime registeredAt;
}