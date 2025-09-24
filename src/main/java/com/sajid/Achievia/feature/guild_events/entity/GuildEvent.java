package com.sajid.Achievia.feature.guild_events.entity;

import com.sajid.Achievia.feature.guilds.entity.Guild;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "guild_events")
public class GuildEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String eventName;
    private LocalDate startDate;
    private LocalDate endDate;
    @ManyToOne @JoinColumn(name = "guild_id", nullable = false)
    private Guild guild;
}