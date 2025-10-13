package com.example.achivia.feature.competitionproblem.service;


import com.example.achivia.feature.competitionproblem.payload.request.CompetitionProblemRequestDTO;
import com.example.achivia.feature.competitionproblem.payload.response.CompetitionProblemResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CompetitionProblemService {
    CompetitionProblemResponseDTO addCompetitionProblem(CompetitionProblemRequestDTO request);
    List<CompetitionProblemResponseDTO> getProblemsByCompetition(UUID competitionId);
    void deleteCompetitionProblem(UUID competitionId, UUID problemId);
}
