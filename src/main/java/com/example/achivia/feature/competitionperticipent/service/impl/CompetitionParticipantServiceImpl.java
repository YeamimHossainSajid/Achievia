package com.example.achivia.feature.competitionperticipent.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.competition.entity.Competition;
import com.example.achivia.feature.competition.repository.CompetitionRepository;
import com.example.achivia.feature.competitionperticipent.entity.CompetitionParticipant;
import com.example.achivia.feature.competitionperticipent.entity.CompetitionParticipantId;
import com.example.achivia.feature.competitionperticipent.payload.request.CompetitionParticipantRequestDTO;
import com.example.achivia.feature.competitionperticipent.payload.response.CompetitionParticipantResponseDTO;
import com.example.achivia.feature.competitionperticipent.repository.CompetitionParticipantRepository;
import com.example.achivia.feature.competitionperticipent.service.CompetitionParticipantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompetitionParticipantServiceImpl implements CompetitionParticipantService {

    private final CompetitionParticipantRepository participantRepository;
    private final CompetitionRepository competitionRepository;
    private final UserRepo userRepository;

    @Override
    public CompetitionParticipantResponseDTO registerParticipant(CompetitionParticipantRequestDTO requestDTO) {
        Competition competition = competitionRepository.findById(requestDTO.getCompetitionId())
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        User user = userRepository.findById(requestDTO.getUserId());

        CompetitionParticipantId id = new CompetitionParticipantId(requestDTO.getCompetitionId(), requestDTO.getUserId());

        CompetitionParticipant participant = CompetitionParticipant.builder()
                .id(id)
                .competition(competition)
                .user(user)
                .totalScore(requestDTO.getTotalScore() != null ? requestDTO.getTotalScore() : 0)
                .build();

        participantRepository.save(participant);
        return mapToResponseDTO(participant);
    }

    @Override
    public List<CompetitionParticipantResponseDTO> getAllParticipants() {
        return participantRepository.findAllParticipants()
                .stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CompetitionParticipantResponseDTO getParticipant(UUID competitionId, UUID userId) {
        CompetitionParticipant participant = participantRepository.findByCompetitionIdAndUserId(competitionId, userId);
        if (participant == null) throw new RuntimeException("Participant not found");
        return mapToResponseDTO(participant);
    }

    @Override
    public void updateScore(UUID competitionId, UUID userId, Integer score) {
        participantRepository.updateTotalScore(competitionId, userId, score);
    }

    @Override
    public void deleteParticipant(UUID competitionId, UUID userId) {
        participantRepository.deleteByCompetitionIdAndUserId(competitionId, userId);
    }

    private CompetitionParticipantResponseDTO mapToResponseDTO(CompetitionParticipant participant) {
        return CompetitionParticipantResponseDTO.builder()
                .competitionId(participant.getCompetition().getId())
                .userId(participant.getUser().getId())
                .totalScore(participant.getTotalScore())
                .registeredAt(participant.getRegisteredAt())
                .build();
    }
}