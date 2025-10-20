package com.example.achivia.feature.problem.repositroy;

import com.example.achivia.feature.problem.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO problem 
        (id, slug, title, difficulty, statement, constraints, input_format, output_format, creator_user_id, is_published, created_at, updated_at)
        VALUES (:id, :slug, :title, :difficulty, :statement, :constraints, :inputFormat, :outputFormat, :creatorUserId, :isPublished, NOW(), NOW())
        """, nativeQuery = true)
    void insertProblem(
            @Param("id") UUID id,
            @Param("slug") String slug,
            @Param("title") String title,
            @Param("difficulty") String difficulty,
            @Param("statement") String statement,
            @Param("constraints") String constraints,
            @Param("inputFormat") String inputFormat,
            @Param("outputFormat") String outputFormat,
            @Param("creatorUserId") UUID creatorUserId,
            @Param("isPublished") Boolean isPublished
    );

    @Query(value = """
        SELECT * FROM problem
        """, nativeQuery = true)
    List<Problem> findAllProblems();

    @Query(value = """
        SELECT * FROM problem
        WHERE id = :id
        """, nativeQuery = true)
    Optional<Problem> findByIdNative(@Param("id") UUID id);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM problem
        WHERE id = :id
        """, nativeQuery = true)
    void deleteProblemById(@Param("id") UUID id);
}
