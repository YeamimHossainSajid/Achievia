package com.example.achivia.feature.guildmember.entity;

import com.example.achivia.auth.model.User;
import com.example.achivia.feature.guild.entity.Guild;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "guild_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuildMember {
    @EmbeddedId
    private GuildMemberId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("guildId")
    @JoinColumn(name = "guild_id")
    private Guild guild;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Builder.Default
    private String role = "member";

    @CreationTimestamp
    @Column(name = "joined_at", nullable = false, updatable = false)
    private LocalDateTime joinedAt;
}