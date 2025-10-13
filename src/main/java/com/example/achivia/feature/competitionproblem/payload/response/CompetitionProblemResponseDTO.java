package com.example.achivia.feature.competitionproblem.payload.response;

import lombok.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetitionProblemResponseDTO {
    private UUID competitionId;
    private UUID problemId;
    private Integer orderIndex;
    private Integer points;
}
