package com.example.achivia.feature.tag.repository;

import com.example.achivia.feature.tag.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO tag (id, name)
        VALUES (:id, :name)
        """, nativeQuery = true)
    void insertTag(UUID id, String name);

    @Query(value = """
        SELECT * FROM tag
        WHERE id = :id
        """, nativeQuery = true)
    Optional<Tag> findByIdNative(UUID id);

    @Query(value = """
        SELECT * FROM tag
        ORDER BY name ASC
        """, nativeQuery = true)
    List<Tag> findAllNative();

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM tag
        WHERE id = :id
        """, nativeQuery = true)
    void deleteByIdNative(UUID id);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE tag
        SET name = :name
        WHERE id = :id
        """, nativeQuery = true)
    void updateTag(UUID id, String name);
}
