package com.example.achivia.feature.userachievement.entity;


import com.example.achivia.auth.model.User;
import com.example.achivia.feature.achievement.entity.Achievement;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_achievements")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAchievement {
    @EmbeddedId
    private UserAchievementId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("achievementId")
    @JoinColumn(name = "achievement_id")
    private Achievement achievement;

    @Column(name = "unlocked_at", nullable = false)
    @Builder.Default
    private LocalDateTime unlockedAt = LocalDateTime.now();
}

