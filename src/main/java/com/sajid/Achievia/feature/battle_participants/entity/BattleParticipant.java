package com.sajid.Achievia.feature.battle_participants.entity;

import com.sajid.Achievia.auth.model.User;
import com.sajid.Achievia.feature.battles.entity.Battle;
import jakarta.persistence.*;

@Entity
@Table(name = "battle_participants")
public class BattleParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer damageDone;
    @ManyToOne @JoinColumn(name = "battle_id", nullable = false)
    private Battle battle;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
}