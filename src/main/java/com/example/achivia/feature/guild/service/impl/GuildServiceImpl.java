package com.example.achivia.feature.guild.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.guild.entity.Guild;
import com.example.achivia.feature.guild.payload.request.GuildRequestDto;
import com.example.achivia.feature.guild.payload.response.GuildResponseDto;
import com.example.achivia.feature.guild.repository.GuildRepository;
import com.example.achivia.feature.guild.service.GuildService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuildServiceImpl implements GuildService {

    private final GuildRepository guildRepository;
    private final UserRepo userRepo;

    private GuildResponseDto convertToDto(Guild guild) {
        return GuildResponseDto.builder()
                .id(guild.getId())
                .name(guild.getName())
                .slug(guild.getSlug())
                .description(guild.getDescription())
                .ownerUserId(guild.getOwnerUser().getId())
                .iconUrl(guild.getIconUrl())
                .createdAt(guild.getCreatedAt())
                .updatedAt(guild.getUpdatedAt())
                .build();
    }

    private Guild convertToEntity(GuildRequestDto dto, User owner) {
        return Guild.builder()
                .name(dto.getName())
                .slug(dto.getSlug())
                .description(dto.getDescription())
                .ownerUser(owner)
                .iconUrl(dto.getIconUrl())
                .build();
    }

    @Override
    public GuildResponseDto createGuild(GuildRequestDto requestDto) {
        User owner = userRepo.findById(requestDto.getOwnerUserId()).get();

        Guild guild = convertToEntity(requestDto, owner);
        Guild saved = guildRepository.save(guild);
        return convertToDto(saved);
    }

    @Override
    public GuildResponseDto getGuildById(UUID id) {
        Guild guild = guildRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Guild not found"));
        return convertToDto(guild);
    }

    @Override
    public GuildResponseDto getGuildBySlug(String slug) {
        Guild guild = guildRepository.findBySlug(slug);
        if (guild == null) throw new EntityNotFoundException("Guild not found");
        return convertToDto(guild);
    }

    @Override
    public List<GuildResponseDto> getGuildsByOwner(Long ownerUserId) {
        return guildRepository.findByOwnerUserId(ownerUserId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuildResponseDto> getAllGuilds() {
        return guildRepository.findAllNative()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GuildResponseDto updateGuild(UUID id, GuildRequestDto requestDto) {
        Guild guild = guildRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Guild not found"));

        if (requestDto.getName() != null) guild.setName(requestDto.getName());
        if (requestDto.getSlug() != null) guild.setSlug(requestDto.getSlug());
        if (requestDto.getDescription() != null) guild.setDescription(requestDto.getDescription());
        if (requestDto.getIconUrl() != null) guild.setIconUrl(requestDto.getIconUrl());

        Guild updated = guildRepository.save(guild);
        return convertToDto(updated);
    }

    @Override
    public void deleteGuild(UUID id) {
        Guild guild = guildRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Guild not found"));
        guildRepository.delete(guild);
    }
}
