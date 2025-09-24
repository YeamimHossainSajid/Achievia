package com.sajid.Achievia.feature.battles.entity;

import com.sajid.Achievia.feature.monsters.entity.Monster;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "battles")
public class Battle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    @ManyToOne @JoinColumn(name = "monster_id", nullable = false)
    private Monster monster;
}