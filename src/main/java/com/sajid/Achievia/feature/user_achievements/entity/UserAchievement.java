package com.sajid.Achievia.feature.user_achievements.entity;

import com.sajid.Achievia.auth.model.User;
import com.sajid.Achievia.feature.achievements.entity.Achievement;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_achievements")
public class UserAchievement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime unlockedAt;
    @ManyToOne @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @ManyToOne @JoinColumn(name = "achievement_id", nullable = false)
    private Achievement achievement;
}