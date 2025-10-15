package com.example.achivia.feature.problemtags.repository;

import com.example.achivia.feature.problemtags.entity.ProblemTag;
import com.example.achivia.feature.problemtags.entity.ProblemTagId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProblemTagRepository extends JpaRepository<ProblemTag, ProblemTagId> {

    @Query(value = "SELECT * FROM problem_tags WHERE problem_id = :problemId", nativeQuery = true)
    List<ProblemTag> findByProblemId(UUID problemId);

    @Query(value = "SELECT * FROM problem_tags WHERE tag_id = :tagId", nativeQuery = true)
    List<ProblemTag> findByTagId(UUID tagId);
}
