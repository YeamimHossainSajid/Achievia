package com.example.achivia.feature.userprofile.repository;

import com.example.achivia.feature.userprofile.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO user_profile
        (user_id, display_name, avatar_url, bio, country_code, timezone, level, xp, streak_days, created_at, updated_at)
        VALUES (:userId, :displayName, :avatarUrl, :bio, :countryCode, :timezone, :level, :xp, :streakDays, :createdAt, :updatedAt)
        """, nativeQuery = true)
    void save(Long userId,
                           String displayName,
                           String avatarUrl,
                           String bio,
                           String countryCode,
                           String timezone,
                           Integer level,
                           Long xp,
                           Integer streakDays,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt);

    @Query(value = """
        SELECT * FROM user_profile
        WHERE user_id = :userId
        """, nativeQuery = true)
    UserProfile findByUserIdNative(Long userId);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE user_profile
        SET display_name = :displayName,
            avatar_url = :avatarUrl,
            bio = :bio,
            country_code = :countryCode,
            timezone = :timezone,
            level = :level,
            xp = :xp,
            streak_days = :streakDays,
            updated_at = :updatedAt
        WHERE user_id = :userId
        """, nativeQuery = true)
    void updateUserProfileNative(Long userId,
                                 String displayName,
                                 String avatarUrl,
                                 String bio,
                                 String countryCode,
                                 String timezone,
                                 Integer level,
                                 Long xp,
                                 Integer streakDays,
                                 LocalDateTime updatedAt);
}

