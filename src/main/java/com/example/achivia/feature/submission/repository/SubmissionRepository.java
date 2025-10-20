package com.example.achivia.feature.submission.repository;

import com.example.achivia.feature.submission.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, UUID> {


    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO submission
        (id, user_id, problem_id, competition_id, language, code, status, score, time_ms, memory_kb, error_message, created_at, updated_at)
        VALUES
        (:id, :userId, :problemId, :competitionId, :language, :code, :status, :score, :timeMs, :memoryKb, :errorMessage, :createdAt, :updatedAt)
        """, nativeQuery = true)
    void insertSubmission(UUID id,
                          UUID userId,
                          UUID problemId,
                          UUID competitionId,
                          String language,
                          String code,
                          String status,
                          Integer score,
                          Integer timeMs,
                          Integer memoryKb,
                          String errorMessage,
                          LocalDateTime createdAt,
                          LocalDateTime updatedAt);

    @Query(value = """
        SELECT * FROM submission
        WHERE id = :id
        """, nativeQuery = true)
    Optional<Submission> findByIdNative(UUID id);

    @Query(value = """
        SELECT * FROM submission
        WHERE user_id = :userId
        ORDER BY created_at DESC
        """, nativeQuery = true)
    List<Submission> findByUserIdNative(UUID userId);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM submission
        WHERE id = :id
        """, nativeQuery = true)
    void deleteByIdNative(UUID id);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE submission
        SET status = :status,
            score = :score,
            time_ms = :timeMs,
            memory_kb = :memoryKb,
            error_message = :errorMessage,
            updated_at = :updatedAt
        WHERE id = :id
        """, nativeQuery = true)
    void update(UUID id,
                          String status,
                          Integer score,
                          Long timeMs,
                          Long memoryKb,
                          String errorMessage,
                          LocalDateTime updatedAt);
}
