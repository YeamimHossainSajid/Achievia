package com.example.achivia.feature.competition.service;


import com.example.achivia.feature.competition.payload.request.CompetitionRequestDto;
import com.example.achivia.feature.competition.payload.response.CompetitionResponseDto;

import java.util.List;
import java.util.UUID;

public interface CompetitionService {

    CompetitionResponseDto createCompetition(CompetitionRequestDto requestDTO);

    CompetitionResponseDto updateCompetition(UUID id, CompetitionRequestDto requestDTO);

    CompetitionResponseDto getCompetitionById(UUID id);

    CompetitionResponseDto getCompetitionBySlug(String slug);

    List<CompetitionResponseDto> getAllCompetitions();

    List<CompetitionResponseDto> getCompetitionsByHost(UUID hostUserId);

    void deleteCompetitionById(UUID id);

    void deleteCompetitionBySlug(String slug);
}

