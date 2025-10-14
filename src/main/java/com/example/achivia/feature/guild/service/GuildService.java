package com.example.achivia.feature.guild.service;

import com.example.achivia.feature.guild.payload.request.GuildRequestDto;
import com.example.achivia.feature.guild.payload.response.GuildResponseDto;

import java.util.List;
import java.util.UUID;

public interface GuildService {

    GuildResponseDto createGuild(GuildRequestDto requestDto);

    GuildResponseDto getGuildById(UUID id);

    GuildResponseDto getGuildBySlug(String slug);

    List<GuildResponseDto> getGuildsByOwner(Long ownerUserId);

    List<GuildResponseDto> getAllGuilds();

    GuildResponseDto updateGuild(UUID id, GuildRequestDto requestDto);

    void deleteGuild(UUID id);
}
