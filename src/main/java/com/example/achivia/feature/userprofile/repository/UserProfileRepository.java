package com.example.achivia.feature.userprofile.repository;

import com.example.achivia.feature.userprofile.entity.UserProfile;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {

    @Query(value = "SELECT * FROM user_profiles WHERE user_id = :userId", nativeQuery = true)
    UserProfile findByUserIdNative(@Param("userId") Long userId);

    @Modifying
    @Query(value = "UPDATE user_profiles SET display_name = :displayName, avatar_url = :avatarUrl, bio = :bio, country_code = :countryCode, timezone = :timezone, level = :level, xp = :xp, streak_days = :streakDays, last_active_at = :lastActiveAt WHERE user_id = :userId", nativeQuery = true)
    void updateUserProfileNative(@Param("userId") Long userId,
                                 @Param("displayName") String displayName,
                                 @Param("avatarUrl") String avatarUrl,
                                 @Param("bio") String bio,
                                 @Param("countryCode") String countryCode,
                                 @Param("timezone") String timezone,
                                 @Param("level") Integer level,
                                 @Param("xp") Long xp,
                                 @Param("streakDays") Integer streakDays,
                                 @Param("lastActiveAt") LocalDateTime lastActiveAt);
}
