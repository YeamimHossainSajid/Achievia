package com.example.achivia.feature.userachievement.repository;

import com.example.achivia.feature.userachievement.entity.UserAchievement;
import com.example.achivia.feature.userachievement.entity.UserAchievementId;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserAchievementRepository extends JpaRepository<UserAchievement, UserAchievementId> {

    @Query(value = "SELECT * FROM user_achievements WHERE user_id = :userId", nativeQuery = true)
    List<UserAchievement> findByUserIdNative(@Param("userId") UUID userId);

    @Query(value = "SELECT * FROM user_achievements WHERE user_id = :userId AND achievement_id = :achievementId", nativeQuery = true)
    UserAchievement findByUserIdAndAchievementIdNative(@Param("userId") UUID userId,
                                                       @Param("achievementId") UUID achievementId);

    @Modifying
    @Query(value = "INSERT INTO user_achievements (user_id, achievement_id, unlocked_at) VALUES (:userId, :achievementId, :unlockedAt)", nativeQuery = true)
    void insertUserAchievement(@Param("userId") UUID userId,
                               @Param("achievementId") UUID achievementId,
                               @Param("unlockedAt") LocalDateTime unlockedAt);
}
