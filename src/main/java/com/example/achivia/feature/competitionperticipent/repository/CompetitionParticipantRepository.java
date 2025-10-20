package com.example.achivia.feature.competitionperticipent.repository;

import com.example.achivia.feature.competitionperticipent.entity.CompetitionParticipant;
import com.example.achivia.feature.competitionperticipent.entity.CompetitionParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompetitionParticipantRepository extends JpaRepository<CompetitionParticipant, CompetitionParticipantId> {

    @Modifying
    @Query(value = """
        INSERT INTO competition_participant (competition_id, user_id, total_score, registered_at)
        SELECT :competitionId, :userId, COALESCE(:totalScore, 0), NOW()
        FROM dual
        WHERE EXISTS (SELECT 1 FROM competition WHERE id = :competitionId)
          AND EXISTS (SELECT 1 FROM users WHERE id = :userId)
        """, nativeQuery = true)
    int saveIfValid(
            @Param("competitionId") UUID competitionId,
            @Param("userId") UUID userId,
            @Param("totalScore") Integer totalScore
    );

    @Query(value = """
            SELECT *
            FROM competition_participants
            """, nativeQuery = true)
    List<CompetitionParticipant> findAllParticipants();


    @Query(value = """
            SELECT *
            FROM competition_participants
            WHERE competition_id = :competitionId
              AND user_id = :userId
            """, nativeQuery = true)
    CompetitionParticipant findByCompetitionIdAndUserId(@Param("competitionId") UUID competitionId,
                                                        @Param("userId") UUID userId);

    @Modifying
    @Query(value = """
            UPDATE competition_participants
            SET total_score = :score
            WHERE competition_id = :competitionId
              AND user_id = :userId
            """, nativeQuery = true)
    void updateTotalScore(@Param("competitionId") UUID competitionId,
                          @Param("userId") UUID userId,
                          @Param("score") Integer score);

    @Modifying
    @Query(value = """
            DELETE FROM competition_participants
            WHERE competition_id = :competitionId
              AND user_id = :userId
            """, nativeQuery = true)
    void deleteByCompetitionIdAndUserId(@Param("competitionId") UUID competitionId,
                                        @Param("userId") UUID userId);

    @Modifying
    @Query(value = """
            INSERT INTO competition_participants (competition_id, user_id, total_score, registered_at)
            VALUES (:competitionId, :userId, :totalScore, NOW())
            """, nativeQuery = true)
    void save(@Param("competitionId") UUID competitionId,
                             @Param("userId") UUID userId,
                             @Param("totalScore") Integer totalScore);


}

