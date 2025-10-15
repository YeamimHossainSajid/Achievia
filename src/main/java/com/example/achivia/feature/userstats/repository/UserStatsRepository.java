package com.example.achivia.feature.userstats.repository;

import com.example.achivia.feature.userstats.entity.UserStats;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface UserStatsRepository extends JpaRepository<UserStats, UUID> {

    @Query(value = "SELECT * FROM user_stats WHERE user_id = :userId", nativeQuery = true)
    UserStats findByUserIdNative(@Param("userId") Long userId);

    @Modifying
    @Query(value = "UPDATE user_stats SET total_points = :totalPoints, " +
            "total_submissions = :totalSubmissions, solved_problems = :solvedProblems," +
            " competition_points = :competitionPoints, habit_points = :habitPoints," +
            " last_recalculated_at = :lastRecalculatedAt WHERE user_id = :userId", nativeQuery = true)
    void updateUserStatsNative(@Param("userId") Long userId,
                               @Param("totalPoints") Long totalPoints,
                               @Param("totalSubmissions") Integer totalSubmissions,
                               @Param("solvedProblems") Integer solvedProblems,
                               @Param("competitionPoints") Long competitionPoints,
                               @Param("habitPoints") Long habitPoints,
                               @Param("lastRecalculatedAt") LocalDateTime lastRecalculatedAt);
}
