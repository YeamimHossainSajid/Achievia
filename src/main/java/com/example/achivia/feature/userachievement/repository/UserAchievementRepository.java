package com.example.achivia.feature.userachievement.repository;

import com.example.achivia.feature.userachievement.entity.UserAchievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievement, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO user_achievement (user_id, achievement_id, unlocked_at)
        VALUES (:userId, :achievementId, :unlockedAt)
        """, nativeQuery = true)
    void insertUserAchievement(UUID userId, UUID achievementId, LocalDateTime unlockedAt);

    @Query(value = """
        SELECT * FROM user_achievement
        WHERE user_id = :userId
        """, nativeQuery = true)
    List<UserAchievement> findByUserIdNative(UUID userId);

    @Query(value = """
        SELECT * FROM user_achievement
        WHERE user_id = :userId
          AND achievement_id = :achievementId
        """, nativeQuery = true)
    UserAchievement findByUserIdAndAchievementIdNative(UUID userId, UUID achievementId);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM user_achievement
        WHERE user_id = :userId
          AND achievement_id = :achievementId
        """, nativeQuery = true)
    void deleteByUserIdAndAchievementId(UUID userId, UUID achievementId);
}
