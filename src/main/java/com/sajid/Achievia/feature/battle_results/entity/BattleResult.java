package com.sajid.Achievia.feature.battle_results.entity;

import com.sajid.Achievia.feature.battles.entity.Battle;
import jakarta.persistence.*;

@Entity
@Table(name = "battle_results")
public class BattleResult {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String winner;
    @ManyToOne
    @JoinColumn(name = "battle_id", nullable = false)
    private Battle battle;
}