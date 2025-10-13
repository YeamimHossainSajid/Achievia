package com.example.achivia.feature.competitionproblem.payload.request;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetitionProblemRequestDTO {
    private UUID competitionId;
    private UUID problemId;
    private Integer orderIndex;
    private Integer points;
}
