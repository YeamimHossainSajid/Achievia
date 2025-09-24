package com.sajid.Achievia.feature.guild_members.entity;

import com.sajid.Achievia.auth.model.User;
import com.sajid.Achievia.feature.guilds.entity.Guild;
import jakarta.persistence.*;

@Entity
@Table(name = "guild_members")
public class GuildMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String role;
    @ManyToOne @JoinColumn(name = "guild_id", nullable = false)
    private Guild guild;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
}