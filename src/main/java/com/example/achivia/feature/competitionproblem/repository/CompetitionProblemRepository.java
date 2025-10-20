package com.example.achivia.feature.competitionproblem.repository;

import com.example.achivia.feature.competitionproblem.entity.CompetitionProblem;
import com.example.achivia.feature.competitionproblem.entity.CompetitionProblemId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompetitionProblemRepository extends JpaRepository<CompetitionProblem, CompetitionProblemId> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO competition_problem (competition_id, problem_id, order_index, points)
        VALUES (:competitionId, :problemId, :orderIndex, :points)
        """, nativeQuery = true)
    void save(
            @Param("competitionId") UUID competitionId,
            @Param("problemId") UUID problemId,
            @Param("orderIndex") Integer orderIndex,
            @Param("points") Integer points
    );

    @Query(value = """
        SELECT * FROM competition_problem
        WHERE competition_id = :competitionId
        ORDER BY order_index ASC
        """, nativeQuery = true)
    List<CompetitionProblem> findByCompetitionId(@Param("competitionId") UUID competitionId);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM competition_problem
        WHERE competition_id = :competitionId AND problem_id = :problemId
        """, nativeQuery = true)
    void deleteByIds(
            @Param("competitionId") UUID competitionId,
            @Param("problemId") UUID problemId
    );
}
