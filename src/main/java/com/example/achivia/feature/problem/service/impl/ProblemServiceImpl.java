package com.example.achivia.feature.problem.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.problem.entity.Problem;
import com.example.achivia.feature.problem.payload.request.ProblemRequestDTO;
import com.example.achivia.feature.problem.payload.response.ProblemResponseDTO;
import com.example.achivia.feature.problem.repositroy.ProblemRepository;
import com.example.achivia.feature.problem.service.ProblemService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;
    private final UserRepo userRepository;

    @Override
    public ProblemResponseDTO createProblem(ProblemRequestDTO request) {
        UUID newId = UUID.randomUUID();

        User creator = userRepository.findById(request.getCreatorUserId());

        problemRepository.insertProblem(
                newId,
                request.getSlug(),
                request.getTitle(),
                request.getDifficulty(),
                request.getStatement(),
                request.getConstraints(),
                request.getInputFormat(),
                request.getOutputFormat(),
                request.getCreatorUserId(),
                request.getIsPublished() != null ? request.getIsPublished() : false
        );

        return ProblemResponseDTO.builder()
                .id(newId)
                .slug(request.getSlug())
                .title(request.getTitle())
                .difficulty(request.getDifficulty())
                .statement(request.getStatement())
                .constraints(request.getConstraints())
                .inputFormat(request.getInputFormat())
                .outputFormat(request.getOutputFormat())
                .creatorUserId(request.getCreatorUserId())
                .isPublished(request.getIsPublished())
                .createdAt(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime())
                .updatedAt(new Date().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime())
                .build();
    }

    @Override
    public List<ProblemResponseDTO> getAllProblems() {
        return problemRepository.findAllProblems()
                .stream()
                .map(problem -> ProblemResponseDTO.builder()
                        .id(problem.getId())
                        .slug(problem.getSlug())
                        .title(problem.getTitle())
                        .difficulty(problem.getDifficulty())
                        .statement(problem.getStatement())
                        .constraints(problem.getConstraints())
                        .inputFormat(problem.getInputFormat())
                        .outputFormat(problem.getOutputFormat())
                        .creatorUserId(problem.getCreatorUser().getId())
                        .isPublished(problem.getIsPublished())
                        .createdAt(problem.getCreatedAt())
                        .updatedAt(problem.getUpdatedAt())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public ProblemResponseDTO getProblemById(UUID id) {
        Problem problem = problemRepository.findByIdNative(id)
                .orElseThrow(() -> new RuntimeException("Problem not found"));

        return ProblemResponseDTO.builder()
                .id(problem.getId())
                .slug(problem.getSlug())
                .title(problem.getTitle())
                .difficulty(problem.getDifficulty())
                .statement(problem.getStatement())
                .constraints(problem.getConstraints())
                .inputFormat(problem.getInputFormat())
                .outputFormat(problem.getOutputFormat())
                .creatorUserId(problem.getCreatorUser().getId())
                .isPublished(problem.getIsPublished())
                .createdAt(problem.getCreatedAt())
                .updatedAt(problem.getUpdatedAt())
                .build();
    }

    @Override
    public void deleteProblem(UUID id) {
        problemRepository.deleteProblemById(id);
    }
}
