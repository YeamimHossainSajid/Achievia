package com.sajid.Achievia.feature.user_stats.entity;

import com.sajid.Achievia.auth.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "user_stats")
public class UserStat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer level;
    private Integer xp;
    private Integer coins;
    private Integer health;
    private Integer mana;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}