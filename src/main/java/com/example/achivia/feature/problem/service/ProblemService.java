package com.example.achivia.feature.problem.service;

import com.example.achivia.feature.problem.payload.request.ProblemRequestDTO;
import com.example.achivia.feature.problem.payload.response.ProblemResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProblemService {
    ProblemResponseDTO createProblem(ProblemRequestDTO request);
    List<ProblemResponseDTO> getAllProblems();
    ProblemResponseDTO getProblemById(UUID id);
    void deleteProblem(UUID id);
}
