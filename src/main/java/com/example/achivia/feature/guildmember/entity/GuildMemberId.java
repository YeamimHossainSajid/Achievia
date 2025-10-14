package com.example.achivia.feature.guildmember.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuildMemberId implements java.io.Serializable {
    private UUID guildId;
    private UUID userId;
}