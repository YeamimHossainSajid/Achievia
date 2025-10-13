package com.example.achivia.feature.competitionperticipent.payload.request;

import lombok.Data;

import java.util.UUID;

@Data
public class CompetitionParticipantRequestDTO {
    private UUID competitionId;
    private UUID userId;
    private Integer totalScore;
}