package com.example.achivia.feature.problemtest.repository;

import com.example.achivia.feature.problemtest.entity.ProblemTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProblemTestRepository extends JpaRepository<ProblemTest, UUID> {

    @Query(value = "SELECT * FROM problem_tests WHERE problem_id = :problemId", nativeQuery = true)
    List<ProblemTest> findByProblemId(UUID problemId);

    @Query(value = "SELECT * FROM problem_tests WHERE problem_id = :problemId AND is_sample = true", nativeQuery = true)
    List<ProblemTest> findSampleTestsByProblemId(UUID problemId);
}
