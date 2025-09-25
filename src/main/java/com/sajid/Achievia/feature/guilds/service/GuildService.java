package com.sajid.Achievia.feature.guilds.service;

import com.sajid.Achievia.feature.guilds.entity.Guild;
import com.sajid.Achievia.feature.guilds.payload.request.GuildRequestDto;
import com.sajid.Achievia.feature.guilds.payload.response.GuildResponseDto;

import java.util.List;

public interface GuildService {

    public void createGuild(GuildRequestDto guildRequestDto);
    public void updateGuild(Long id, GuildRequestDto guildRequestDto);
    public void deleteGuild(Long id);
    public List<GuildResponseDto> getGuilds();
    public GuildResponseDto getGuildById(Long guildId);

}
