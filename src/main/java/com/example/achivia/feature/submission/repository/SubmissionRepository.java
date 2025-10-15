package com.example.achivia.feature.submission.repository;

import com.example.achivia.feature.submission.entity.Submission;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, UUID> {

    @Query(value = "SELECT * FROM submissions WHERE id = :id", nativeQuery = true)
    Optional<Submission> findByIdNative(@Param("id") UUID id);

    @Query(value = "SELECT * FROM submissions WHERE user_id = :userId ORDER BY created_at DESC", nativeQuery = true)
    List<Submission> findByUserIdNative(@Param("userId") UUID userId);

    @Modifying
    @Query(value = """
        INSERT INTO submissions
        (id, user_id, problem_id, competition_id, language, code, status, score, time_ms, memory_kb, error_message, created_at, updated_at)
        VALUES (:id, :userId, :problemId, :competitionId, :language, :code, :status, :score, :timeMs, :memoryKb, :errorMessage, :createdAt, :updatedAt)
        """, nativeQuery = true)
    void insertSubmission(
            @Param("id") UUID id,
            @Param("userId") UUID userId,
            @Param("problemId") UUID problemId,
            @Param("competitionId") UUID competitionId,
            @Param("language") String language,
            @Param("code") String code,
            @Param("status") String status,
            @Param("score") Integer score,
            @Param("timeMs") Integer timeMs,
            @Param("memoryKb") Integer memoryKb,
            @Param("errorMessage") String errorMessage,
            @Param("createdAt") java.time.LocalDateTime createdAt,
            @Param("updatedAt") java.time.LocalDateTime updatedAt
    );
}
