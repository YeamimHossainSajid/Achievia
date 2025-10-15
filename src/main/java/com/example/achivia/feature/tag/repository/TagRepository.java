package com.example.achivia.feature.tag.repository;

import com.example.achivia.feature.tag.entity.Tag;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {

    @Query(value = "SELECT * FROM tags WHERE id = :id", nativeQuery = true)
    Optional<Tag> findByIdNative(@Param("id") UUID id);

    @Query(value = "SELECT * FROM tags WHERE name = :name", nativeQuery = true)
    Optional<Tag> findByNameNative(@Param("name") String name);

    @Query(value = "SELECT * FROM tags ORDER BY name ASC", nativeQuery = true)
    List<Tag> findAllNative();

    @Modifying
    @Query(value = "INSERT INTO tags (id, name) VALUES (:id, :name)", nativeQuery = true)
    void insertTag(@Param("id") UUID id, @Param("name") String name);
}
