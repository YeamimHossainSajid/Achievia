package com.example.achivia.feature.competitionperticipent.controller;

import com.example.achivia.feature.competitionperticipent.payload.request.CompetitionParticipantRequestDTO;
import com.example.achivia.feature.competitionperticipent.payload.response.CompetitionParticipantResponseDTO;
import com.example.achivia.feature.competitionperticipent.service.CompetitionParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/participants")
@RequiredArgsConstructor
public class CompetitionParticipantController {

    private final CompetitionParticipantService participantService;

    @PostMapping
    public ResponseEntity<CompetitionParticipantResponseDTO> registerParticipant(@RequestBody CompetitionParticipantRequestDTO requestDTO) {
        return ResponseEntity.ok(participantService.registerParticipant(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<CompetitionParticipantResponseDTO>> getAllParticipants() {
        return ResponseEntity.ok(participantService.getAllParticipants());
    }

    @GetMapping("/{competitionId}/{userId}")
    public ResponseEntity<CompetitionParticipantResponseDTO> getParticipant(
            @PathVariable UUID competitionId,
            @PathVariable UUID userId) {
        return ResponseEntity.ok(participantService.getParticipant(competitionId, userId));
    }

    @PutMapping("/{competitionId}/{userId}/score")
    public ResponseEntity<Void> updateScore(
            @PathVariable UUID competitionId,
            @PathVariable UUID userId,
            @RequestParam Integer score) {
        participantService.updateScore(competitionId, userId, score);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{competitionId}/{userId}")
    public ResponseEntity<Void> deleteParticipant(
            @PathVariable UUID competitionId,
            @PathVariable UUID userId) {
        participantService.deleteParticipant(competitionId, userId);
        return ResponseEntity.ok().build();
    }
}
