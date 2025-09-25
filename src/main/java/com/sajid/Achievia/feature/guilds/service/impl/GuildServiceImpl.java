package com.sajid.Achievia.feature.guilds.service.impl;

import com.sajid.Achievia.feature.guilds.entity.Guild;
import com.sajid.Achievia.feature.guilds.payload.request.GuildRequestDto;
import com.sajid.Achievia.feature.guilds.payload.response.GuildResponseDto;
import com.sajid.Achievia.feature.guilds.repository.GuildRepository;
import com.sajid.Achievia.feature.guilds.service.GuildService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GuildServiceImpl implements GuildService {

    private final GuildRepository guildRepository;

    public GuildServiceImpl(GuildRepository guildRepository) {
        this.guildRepository = guildRepository;
    }

    private Guild convertToGuild(GuildRequestDto guildRequestDto) {
        Guild guild = new Guild();
        guild.setName(guildRequestDto.getName());
        guild.setCreatedAt(guildRequestDto.getCreatedAt());
        return guild;
    }

    private GuildResponseDto convertToGuildResponseDto(Guild guild) {
        GuildResponseDto guildResponseDto = new GuildResponseDto();
        guildResponseDto.setId(guild.getId());
        guildResponseDto.setName(guild.getName());
        guildResponseDto.setCreatedAt(guild.getCreatedAt());
        return guildResponseDto;
    }

    @Override
    public void createGuild(GuildRequestDto guildRequestDto) {
        Guild guild = convertToGuild(guildRequestDto);
        guildRepository.save(guild);
    }

    @Override
    public void updateGuild(Long id,GuildRequestDto guildRequestDto) {
        Guild guild = guildRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Guild not found with id: " + id));

        guild.setName(guildRequestDto.getName());
        guild.setCreatedAt(guildRequestDto.getCreatedAt());

        guildRepository.save(guild);
    }

    @Override
    public void deleteGuild(Long id) {
        if (!guildRepository.existsById(id)) {
            throw new RuntimeException("Guild not found with id: " + id);
        }
        guildRepository.deleteById(id);
    }

    @Override
    public List<GuildResponseDto> getGuilds() {
        return guildRepository.findAll()
                .stream()
                .map(this::convertToGuildResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public GuildResponseDto getGuildById(Long guildId) {
        Guild guild = guildRepository.findById(guildId)
                .orElseThrow(() -> new RuntimeException("Guild not found with id: " + guildId));
        return convertToGuildResponseDto(guild);
    }
}

