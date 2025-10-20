package com.example.achivia.feature.userstats.repository;

import com.example.achivia.feature.userstats.entity.UserStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Repository
public interface UserStatsRepository extends JpaRepository<UserStats, Long> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO user_stats
        (user_id, total_points, total_submissions, solved_problems, competition_points, habit_points, last_recalculated_at, created_at, updated_at)
        VALUES (:userId, :totalPoints, :totalSubmissions, :solvedProblems, :competitionPoints, :habitPoints, :lastRecalculatedAt, :createdAt, :updatedAt)
        """, nativeQuery = true)
    void save(Long userId,
                         Long totalPoints,
                         Integer totalSubmissions,
                         Integer solvedProblems,
                         Long competitionPoints,
                         Long habitPoints,
                         LocalDateTime lastRecalculatedAt,
                         LocalDateTime createdAt,
                         LocalDateTime updatedAt);

    @Query(value = """
        SELECT * FROM user_stats
        WHERE user_id = :userId
        """, nativeQuery = true)
    UserStats findByUserIdNative(Long userId);


    @Modifying
    @Transactional
    @Query(value = """
        UPDATE user_stats
        SET total_points = :totalPoints,
            total_submissions = :totalSubmissions,
            solved_problems = :solvedProblems,
            competition_points = :competitionPoints,
            habit_points = :habitPoints,
            last_recalculated_at = :lastRecalculatedAt,
            updated_at = :lastRecalculatedAt
        WHERE user_id = :userId
        """, nativeQuery = true)
    void updateUserStatsNative(Long userId,
                               Long totalPoints,
                               Integer totalSubmissions,
                               Integer solvedProblems,
                               Long competitionPoints,
                               Long habitPoints,
                               LocalDateTime lastRecalculatedAt);
}
