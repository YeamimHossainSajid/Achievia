package com.example.achivia.feature.competitionperticipent.service;

import com.example.achivia.feature.competitionperticipent.payload.request.CompetitionParticipantRequestDTO;
import com.example.achivia.feature.competitionperticipent.payload.response.CompetitionParticipantResponseDTO;

import java.util.List;
import java.util.UUID;

public interface CompetitionParticipantService {
    CompetitionParticipantResponseDTO registerParticipant(CompetitionParticipantRequestDTO requestDTO);
    List<CompetitionParticipantResponseDTO> getAllParticipants();
    CompetitionParticipantResponseDTO getParticipant(UUID competitionId, UUID userId);
    void updateScore(UUID competitionId, UUID userId, Integer score);
    void deleteParticipant(UUID competitionId, UUID userId);
}