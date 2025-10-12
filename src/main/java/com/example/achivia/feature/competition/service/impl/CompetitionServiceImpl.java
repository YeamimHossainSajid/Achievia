package com.example.achivia.feature.competition.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.competition.entity.Competition;
import com.example.achivia.feature.competition.payload.request.CompetitionRequestDto;
import com.example.achivia.feature.competition.payload.response.CompetitionResponseDto;
import com.example.achivia.feature.competition.repository.CompetitionRepository;
import com.example.achivia.feature.competition.service.CompetitionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final UserRepo userRepository;

    @Override
    public CompetitionResponseDto createCompetition(CompetitionRequestDto requestDTO) {
        UUID id = UUID.randomUUID();
        User hostUser = userRepository.findById(requestDTO.getHostUserId());

        Competition competition = convertToEntity(requestDTO, hostUser);
        competitionRepository.insertCompetition(
                id,
                competition.getSlug(),
                competition.getName(),
                competition.getDescription(),
                competition.getStartAt(),
                competition.getEndAt(),
                competition.getVisibility(),
                hostUser.getId()
        );

        Competition saved = competitionRepository.findCompetitionById(id)
                .orElseThrow(() -> new RuntimeException("Competition not created"));
        return convertToDto(saved);
    }

    @Override
    public CompetitionResponseDto updateCompetition(UUID id, CompetitionRequestDto requestDTO) {
        competitionRepository.findCompetitionById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));

        User hostUser = userRepository.findById(requestDTO.getHostUserId());

        Competition competition = convertToEntity(requestDTO, hostUser);

        competitionRepository.updateCompetition(
                id,
                competition.getSlug(),
                competition.getName(),
                competition.getDescription(),
                competition.getStartAt(),
                competition.getEndAt(),
                competition.getVisibility(),
                hostUser.getId()
        );

        Competition updated = competitionRepository.findCompetitionById(id)
                .orElseThrow(() -> new RuntimeException("Error fetching updated competition"));
        return convertToDto(updated);
    }

    @Override
    public CompetitionResponseDto getCompetitionById(UUID id) {
        Competition competition = competitionRepository.findCompetitionById(id)
                .orElseThrow(() -> new RuntimeException("Competition not found"));
        return convertToDto(competition);
    }

    @Override
    public CompetitionResponseDto getCompetitionBySlug(String slug) {
        Competition competition = competitionRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Competition not found"));
        return convertToDto(competition);
    }

    @Override
    public List<CompetitionResponseDto> getAllCompetitions() {
        List<Competition> competitions = competitionRepository.findAllCompetitions();
        return competitions.stream().map(this::convertToDto).toList();
    }

    @Override
    public List<CompetitionResponseDto> getCompetitionsByHost(UUID hostUserId) {
        List<Competition> competitions = competitionRepository.findByHostUserId(hostUserId);
        return competitions.stream().map(this::convertToDto).toList();
    }

    @Override
    public void deleteCompetitionById(UUID id) {
        competitionRepository.deleteCompetitionById(id);
    }

    @Override
    public void deleteCompetitionBySlug(String slug) {
        competitionRepository.deleteCompetitionBySlug(slug);
    }


    private Competition convertToEntity(CompetitionRequestDto dto, User hostUser) {
        Competition competition = new Competition();
        competition.setSlug(dto.getSlug());
        competition.setName(dto.getName());
        competition.setDescription(dto.getDescription());
        competition.setStartAt(dto.getStartAt());
        competition.setEndAt(dto.getEndAt());
        competition.setVisibility(dto.getVisibility() != null ? dto.getVisibility() : "public");
        competition.setHostUser(hostUser);
        return competition;
    }

    private CompetitionResponseDto convertToDto(Competition competition) {
        CompetitionResponseDto dto = new CompetitionResponseDto();
        dto.setId(competition.getId());
        dto.setSlug(competition.getSlug());
        dto.setName(competition.getName());
        dto.setDescription(competition.getDescription());
        dto.setStartAt(competition.getStartAt());
        dto.setEndAt(competition.getEndAt());
        dto.setVisibility(competition.getVisibility());
        dto.setCreatedAt(competition.getCreatedAt());
        dto.setUpdatedAt(competition.getUpdatedAt());

        if (competition.getHostUser() != null) {
            dto.setHostUserId(competition.getHostUser().getId());
            dto.setHostUserName(competition.getHostUser().getUsername());
        }

        return dto;
    }
}

