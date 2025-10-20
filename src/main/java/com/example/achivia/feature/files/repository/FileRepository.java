package com.example.achivia.feature.files.repository;

import com.example.achivia.feature.files.entity.File;
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
public interface FileRepository extends JpaRepository<File, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO file (id, uploader_user_id, url, mime_type, size_bytes, created_at)
        VALUES (:id, :uploaderUserId, :url, :mimeType, :sizeBytes, NOW())
        """, nativeQuery = true)
    void save(
            @Param("id") UUID id,
            @Param("uploaderUserId") UUID uploaderUserId,
            @Param("url") String url,
            @Param("mimeType") String mimeType,
            @Param("sizeBytes") Long sizeBytes
    );

    @Query(value = """
        SELECT * FROM file
        WHERE id = :id
        """, nativeQuery = true)
    Optional<File> findById(@Param("id") UUID id);

    @Query(value = """
        SELECT * FROM file
        ORDER BY created_at DESC
        """, nativeQuery = true)
    List<File> findAllNative();

    @Query(value = """
        SELECT * FROM file
        WHERE uploader_user_id = :userId
        ORDER BY created_at DESC
        """, nativeQuery = true)
    List<File> findAllByUploaderUserId(@Param("userId") UUID userId);

    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM file
        WHERE id = :id
        """, nativeQuery = true)
    void deleteById(@Param("id") UUID id);
}
