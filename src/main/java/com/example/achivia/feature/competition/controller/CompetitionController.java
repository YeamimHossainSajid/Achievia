package com.example.achivia.feature.competition.controller;

import com.example.achivia.feature.competition.payload.request.CompetitionRequestDto;
import com.example.achivia.feature.competition.payload.response.CompetitionResponseDto;
import com.example.achivia.feature.competition.service.CompetitionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/competitions")
@RequiredArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    @PostMapping
    public ResponseEntity<CompetitionResponseDto> createCompetition(
            @Valid @RequestBody CompetitionRequestDto requestDTO) {

        CompetitionResponseDto response = competitionService.createCompetition(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompetitionResponseDto> updateCompetition(
            @PathVariable UUID id,
            @Valid @RequestBody CompetitionRequestDto requestDTO) {

        CompetitionResponseDto response = competitionService.updateCompetition(id, requestDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<CompetitionResponseDto>> getAllCompetitions() {
        return ResponseEntity.ok(competitionService.getAllCompetitions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompetitionResponseDto> getCompetitionById(@PathVariable UUID id) {
        return ResponseEntity.ok(competitionService.getCompetitionById(id));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<CompetitionResponseDto> getCompetitionBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(competitionService.getCompetitionBySlug(slug));
    }

    @GetMapping("/host/{hostUserId}")
    public ResponseEntity<List<CompetitionResponseDto>> getCompetitionsByHost(@PathVariable UUID hostUserId) {
        return ResponseEntity.ok(competitionService.getCompetitionsByHost(hostUserId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetitionById(@PathVariable UUID id) {
        competitionService.deleteCompetitionById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/slug/{slug}")
    public ResponseEntity<Void> deleteCompetitionBySlug(@PathVariable String slug) {
        competitionService.deleteCompetitionBySlug(slug);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
