package com.example.achivia.feature.competitionperticipent.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class CompetitionParticipantId implements java.io.Serializable {
    private UUID competitionId;
    private UUID userId;
}