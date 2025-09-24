package com.sajid.Achievia.feature.guild_roles.entity;

import com.sajid.Achievia.feature.guilds.entity.Guild;
import jakarta.persistence.*;

@Entity
@Table(name = "guild_roles")
public class GuildRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;
    @ManyToOne
    @JoinColumn(name = "guild_id", nullable = false)
    private Guild guild;
}