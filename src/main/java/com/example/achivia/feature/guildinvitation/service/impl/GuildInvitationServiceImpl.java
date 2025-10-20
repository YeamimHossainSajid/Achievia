package com.example.achivia.feature.guildinvitation.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.guild.entity.Guild;
import com.example.achivia.feature.guild.repository.GuildRepository;
import com.example.achivia.feature.guildinvitation.entity.GuildInvitation;
import com.example.achivia.feature.guildinvitation.payload.request.GuildInvitationRequestDto;
import com.example.achivia.feature.guildinvitation.payload.response.GuildInvitationResponseDto;
import com.example.achivia.feature.guildinvitation.repository.GuildInvitationRepository;
import com.example.achivia.feature.guildinvitation.service.GuildInvitationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuildInvitationServiceImpl implements GuildInvitationService {

    private final GuildInvitationRepository invitationRepository;
    private final GuildRepository guildRepository;
    private final UserRepo userRepo;

    private GuildInvitationResponseDto convertToDto(GuildInvitation invitation) {
        return GuildInvitationResponseDto.builder()
                .id(invitation.getId())
                .guildId(invitation.getGuild().getId())
                .inviterUserId(invitation.getInviterUser().getId())
                .inviteeEmail(invitation.getInviteeEmail())
                .token(invitation.getToken())
                .expiresAt(invitation.getExpiresAt())
                .acceptedAt(invitation.getAcceptedAt())
                .build();
    }

    @Override
    public GuildInvitationResponseDto createInvitation(GuildInvitationRequestDto requestDto) {
        Guild guild = guildRepository.findById(requestDto.getGuildId())
                .orElseThrow(() -> new EntityNotFoundException("Guild not found"));

        User inviter = userRepo.findById(requestDto.getInviterUserId()).get();

        GuildInvitation invitation = GuildInvitation.builder()
                .guild(guild)
                .inviterUser(inviter)
                .inviteeEmail(requestDto.getInviteeEmail())
                .expiresAt(requestDto.getExpiresAt())
                .token(UUID.randomUUID().toString())
                .build();

        GuildInvitation saved = invitationRepository.save(invitation);
        return convertToDto(saved);
    }

    @Override
    public GuildInvitationResponseDto getInvitationById(UUID id) {
        GuildInvitation invitation = invitationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invitation not found"));
        return convertToDto(invitation);
    }

    @Override
    public GuildInvitationResponseDto getInvitationByToken(String token) {
        GuildInvitation invitation = invitationRepository.findByToken(token);
        if (invitation == null) throw new EntityNotFoundException("Invitation not found");
        return convertToDto(invitation);
    }

    @Override
    public List<GuildInvitationResponseDto> getInvitationsByGuild(UUID guildId) {
        return invitationRepository.findByGuildId(guildId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GuildInvitationResponseDto> getInvitationsByEmail(String email) {
        return invitationRepository.findByInviteeEmail(email)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GuildInvitationResponseDto acceptInvitation(UUID id) {
        GuildInvitation invitation = invitationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invitation not found"));

        if (invitation.getAcceptedAt() != null) {
            throw new IllegalStateException("Invitation already accepted");
        }

        invitation.setAcceptedAt(LocalDateTime.now());
        GuildInvitation saved = invitationRepository.save(invitation);
        return convertToDto(saved);
    }

    @Override
    public void deleteInvitation(UUID id) {
        GuildInvitation invitation = invitationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Invitation not found"));
        invitationRepository.delete(invitation);
    }
}
