package com.example.achivia.feature.competitionproblem.entity;

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
public class CompetitionProblemId implements java.io.Serializable {
    private UUID competitionId;
    private UUID problemId;
}
