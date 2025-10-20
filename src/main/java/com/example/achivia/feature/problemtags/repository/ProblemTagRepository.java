package com.example.achivia.feature.problemtags.repository;

import com.example.achivia.feature.problemtags.entity.ProblemTag;
import com.example.achivia.feature.problemtags.entity.ProblemTagId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProblemTagRepository extends JpaRepository<ProblemTag, ProblemTagId> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO problem_tag (problem_id, tag_id)
        VALUES (:problemId, :tagId)
        """, nativeQuery = true)
    void saveNative(UUID problemId, UUID tagId);

    @Query(value = """
        SELECT * FROM problem_tag
        WHERE problem_id = :problemId
        """, nativeQuery = true)
    List<ProblemTag> findByProblemId(UUID problemId);

    @Query(value = """
        SELECT * FROM problem_tag
        WHERE tag_id = :tagId
        """, nativeQuery = true)
    List<ProblemTag> findByTagId(UUID tagId);
}
