package com.example.achivia.feature.problem.controller;

import com.example.achivia.feature.problem.payload.request.ProblemRequestDTO;
import com.example.achivia.feature.problem.payload.response.ProblemResponseDTO;
import com.example.achivia.feature.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @PostMapping
    public ResponseEntity<ProblemResponseDTO> createProblem(@RequestBody ProblemRequestDTO request) {
        return ResponseEntity.ok(problemService.createProblem(request));
    }

    @GetMapping
    public ResponseEntity<List<ProblemResponseDTO>> getAllProblems() {
        return ResponseEntity.ok(problemService.getAllProblems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemResponseDTO> getProblemById(@PathVariable UUID id) {
        return ResponseEntity.ok(problemService.getProblemById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProblem(@PathVariable UUID id) {
        problemService.deleteProblem(id);
        return ResponseEntity.noContent().build();
    }
}
