package com.example.achivia.feature.problemtest.repository;

import com.example.achivia.feature.problemtest.entity.ProblemTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProblemTestRepository extends JpaRepository<ProblemTest, UUID> {

    @Query(value = """
        SELECT * FROM problem_test
        WHERE problem_id = :problemId
        """, nativeQuery = true)
    List<ProblemTest> findByProblemId(UUID problemId);


    @Query(value = """
        SELECT * FROM problem_test
        WHERE problem_id = :problemId
          AND is_sample = true
        """, nativeQuery = true)
    List<ProblemTest> findSampleTestsByProblemId(UUID problemId);

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO problem_test (id, problem_id, is_sample, input, expected_output, score_weight, created_at)
        VALUES (:id, :problemId, :isSample, :input, :expectedOutput, :scoreWeight, NOW())
        """, nativeQuery = true)
    void save(UUID id, UUID problemId, Boolean isSample, String input, String expectedOutput, Integer scoreWeight);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM problem_test
        WHERE id = :id
        """, nativeQuery = true)
    void delete(UUID id);
}
