package com.example.achivia.feature.problem.repositroy;

import com.example.achivia.feature.problem.entity.Problem;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, UUID> {

    @Query(value = "SELECT * FROM problems WHERE id = :id", nativeQuery = true)
    Optional<Problem> findByIdNative(@Param("id") UUID id);

    @Query(value = "SELECT * FROM problems WHERE slug = :slug", nativeQuery = true)
    Optional<Problem> findBySlug(@Param("slug") String slug);

    @Query(value = "SELECT * FROM problems", nativeQuery = true)
    List<Problem> findAllProblems();

    @Modifying
    @Query(value = "INSERT INTO problems (id, slug, title, difficulty, statement, constraints, input_format, output_format, creator_user_id, is_published, created_at, updated_at) " +
                   "VALUES (:id, :slug, :title, :difficulty, :statement, :constraints, :inputFormat, :outputFormat, :creatorUserId, :isPublished, NOW(), NOW())",
           nativeQuery = true)
    void insertProblem(@Param("id") UUID id,
                       @Param("slug") String slug,
                       @Param("title") String title,
                       @Param("difficulty") String difficulty,
                       @Param("statement") String statement,
                       @Param("constraints") String constraints,
                       @Param("inputFormat") String inputFormat,
                       @Param("outputFormat") String outputFormat,
                       @Param("creatorUserId") UUID creatorUserId,
                       @Param("isPublished") Boolean isPublished);

    @Modifying
    @Query(value = "DELETE FROM problems WHERE id = :id", nativeQuery = true)
    void deleteProblemById(@Param("id") UUID id);
}
