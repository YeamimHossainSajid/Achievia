package com.example.achivia.feature.competitionproblem.service.impl;

import com.example.achivia.feature.competition.entity.Competition;
import com.example.achivia.feature.competitionproblem.payload.request.CompetitionProblemRequestDTO;
import com.example.achivia.feature.competitionproblem.payload.response.CompetitionProblemResponseDTO;
import com.example.achivia.feature.problem.entity.Problem;
import com.example.achivia.feature.competition.repository.CompetitionRepository;
import com.example.achivia.feature.competitionproblem.entity.*;
import com.example.achivia.feature.competitionproblem.repository.CompetitionProblemRepository;
import com.example.achivia.feature.competitionproblem.service.CompetitionProblemService;
import com.example.achivia.feature.problem.repositroy.ProblemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CompetitionProblemServiceImpl implements CompetitionProblemService {

    private final CompetitionProblemRepository competitionProblemRepository;
    private final CompetitionRepository competitionRepository;
    private final ProblemRepository problemRepository;

    @Override
    public CompetitionProblemResponseDTO addCompetitionProblem(CompetitionProblemRequestDTO request) {
        Competition competition = competitionRepository.findById(request.getCompetitionId())
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        Problem problem = problemRepository.findById(request.getProblemId())
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        CompetitionProblemId id = CompetitionProblemId.builder()
                .competitionId(request.getCompetitionId())
                .problemId(request.getProblemId())
                .build();

        CompetitionProblem entity = CompetitionProblem.builder()
                .id(id)
                .competition(competition)
                .problem(problem)
                .orderIndex(request.getOrderIndex())
                .points(request.getPoints())
                .build();

        competitionProblemRepository.save(entity);

        return CompetitionProblemResponseDTO.builder()
                .competitionId(request.getCompetitionId())
                .problemId(request.getProblemId())
                .orderIndex(request.getOrderIndex())
                .points(request.getPoints())
                .build();
    }

    @Override
    public List<CompetitionProblemResponseDTO> getProblemsByCompetition(UUID competitionId) {
        return competitionProblemRepository.findByCompetitionId(competitionId)
                .stream()
                .map(cp -> CompetitionProblemResponseDTO.builder()
                        .competitionId(cp.getId().getCompetitionId())
                        .problemId(cp.getId().getProblemId())
                        .orderIndex(cp.getOrderIndex())
                        .points(cp.getPoints())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCompetitionProblem(UUID competitionId, UUID problemId) {
        competitionProblemRepository.deleteByIds(competitionId, problemId);
    }
}
