package com.example.achivia.feature.guildmember.service;

import com.example.achivia.feature.guildmember.payload.request.GuildMemberRequestDto;
import com.example.achivia.feature.guildmember.payload.response.GuildMemberResponseDto;

import java.util.List;
import java.util.UUID;

public interface GuildMemberService {

    GuildMemberResponseDto addMember(GuildMemberRequestDto requestDto);

    List<GuildMemberResponseDto> getMembersByGuild(UUID guildId);

    List<GuildMemberResponseDto> getMembersByUser(UUID userId);

    GuildMemberResponseDto getMember(UUID guildId, UUID userId);

    GuildMemberResponseDto updateMemberRole(UUID guildId, UUID userId, String role);

    void removeMember(UUID guildId, UUID userId);
}
