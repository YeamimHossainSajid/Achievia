package com.example.achivia.feature.competitionproblem.repository;

import com.example.achivia.feature.competitionproblem.entity.CompetitionProblem;
import com.example.achivia.feature.competitionproblem.entity.CompetitionProblemId;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CompetitionProblemRepository extends JpaRepository<CompetitionProblem, CompetitionProblemId> {

    @Query(value = "SELECT * FROM competition_problems WHERE competition_id = :competitionId", nativeQuery = true)
    List<CompetitionProblem> findByCompetitionId(@Param("competitionId") UUID competitionId);

    @Query(value = "SELECT * FROM competition_problems WHERE problem_id = :problemId", nativeQuery = true)
    List<CompetitionProblem> findByProblemId(@Param("problemId") UUID problemId);

    @Modifying
    @Query(value = "DELETE FROM competition_problems WHERE competition_id = :competitionId AND problem_id = :problemId", nativeQuery = true)
    void deleteByIds(@Param("competitionId") UUID competitionId, @Param("problemId") UUID problemId);
}
