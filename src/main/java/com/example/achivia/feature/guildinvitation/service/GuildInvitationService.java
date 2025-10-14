package com.example.achivia.feature.guildinvitation.service;

import com.example.achivia.feature.guildinvitation.payload.request.GuildInvitationRequestDto;
import com.example.achivia.feature.guildinvitation.payload.response.GuildInvitationResponseDto;

import java.util.List;
import java.util.UUID;

public interface GuildInvitationService {

    GuildInvitationResponseDto createInvitation(GuildInvitationRequestDto requestDto);

    GuildInvitationResponseDto getInvitationById(UUID id);

    GuildInvitationResponseDto getInvitationByToken(String token);

    List<GuildInvitationResponseDto> getInvitationsByGuild(UUID guildId);

    List<GuildInvitationResponseDto> getInvitationsByEmail(String email);

    GuildInvitationResponseDto acceptInvitation(UUID id);

    void deleteInvitation(UUID id);
}
