package com.example.achivia.feature.competitionproblem.controller;
import com.example.achivia.feature.competitionproblem.payload.request.CompetitionProblemRequestDTO;
import com.example.achivia.feature.competitionproblem.payload.response.CompetitionProblemResponseDTO;
import com.example.achivia.feature.competitionproblem.service.CompetitionProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/competition-problems")
@RequiredArgsConstructor
public class CompetitionProblemController {

    private final CompetitionProblemService competitionProblemService;

    @PostMapping
    public ResponseEntity<CompetitionProblemResponseDTO> addCompetitionProblem(@RequestBody CompetitionProblemRequestDTO request) {
        return ResponseEntity.ok(competitionProblemService.addCompetitionProblem(request));
    }

    @GetMapping("/{competitionId}")
    public ResponseEntity<List<CompetitionProblemResponseDTO>> getProblemsByCompetition(@PathVariable UUID competitionId) {
        return ResponseEntity.ok(competitionProblemService.getProblemsByCompetition(competitionId));
    }

    @DeleteMapping("/{competitionId}/{problemId}")
    public ResponseEntity<Void> deleteCompetitionProblem(@PathVariable UUID competitionId, @PathVariable UUID problemId) {
        competitionProblemService.deleteCompetitionProblem(competitionId, problemId);
        return ResponseEntity.noContent().build();
    }
}
