package com.example.achivia.feature.guildmember.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.guild.entity.Guild;
import com.example.achivia.feature.guild.repository.GuildRepository;
import com.example.achivia.feature.guildmember.entity.GuildMember;
import com.example.achivia.feature.guildmember.entity.GuildMemberId;
import com.example.achivia.feature.guildmember.payload.request.GuildMemberRequestDto;
import com.example.achivia.feature.guildmember.payload.response.GuildMemberResponseDto;
import com.example.achivia.feature.guildmember.repository.GuildMemberRepository;
import com.example.achivia.feature.guildmember.service.GuildMemberService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuildMemberServiceImpl implements GuildMemberService {

    private final GuildMemberRepository memberRepository;
    private final GuildRepository guildRepository;
    private final UserRepo userRepo;

    private GuildMemberResponseDto convertToDto(GuildMember member) {
        return GuildMemberResponseDto.builder()
                .guildId(member.getGuild().getId())
                .userId(member.getUser().getId())
                .role(member.getRole())
                .joinedAt(member.getJoinedAt())
                .build();
    }

    @Override
    public GuildMemberResponseDto addMember(GuildMemberRequestDto requestDto) {
        Guild guild = guildRepository.findById(requestDto.getGuildId())
                .orElseThrow(() -> new EntityNotFoundException("Guild not found"));

        User user = userRepo.findById(requestDto.getUserId()).get();

        GuildMemberId memberId = new GuildMemberId(guild.getId(), user.getId());

        if (memberRepository.existsById(memberId)) {
            throw new IllegalStateException("User is already a member of this guild");
        }

        GuildMember member = GuildMember.builder()
                .id(memberId)
                .guild(guild)
                .user(user)
                .role(requestDto.getRole() != null ? requestDto.getRole() : "member")
                .build();

        return convertToDto(memberRepository.save(member));
    }

    @Override
    public List<GuildMemberResponseDto> getMembersByGuild(UUID guildId) {
        return memberRepository.findByGuildId(guildId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuildMemberResponseDto> getMembersByUser(UUID userId) {
        return memberRepository.findByUserId(userId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GuildMemberResponseDto getMember(UUID guildId, UUID userId) {
        GuildMember member = memberRepository.findByGuildAndUser(guildId, userId);
        if (member == null) throw new EntityNotFoundException("Member not found");
        return convertToDto(member);
    }

    @Override
    public GuildMemberResponseDto updateMemberRole(UUID guildId, UUID userId, String role) {
        GuildMember member = memberRepository.findByGuildAndUser(guildId, userId);
        if (member == null) throw new EntityNotFoundException("Member not found");
        member.setRole(role);
        return convertToDto(memberRepository.save(member));
    }

    @Override
    public void removeMember(UUID guildId, UUID userId) {
        GuildMember member = memberRepository.findByGuildAndUser(guildId, userId);
        if (member == null) throw new EntityNotFoundException("Member not found");
        memberRepository.delete(member);
    }
}
