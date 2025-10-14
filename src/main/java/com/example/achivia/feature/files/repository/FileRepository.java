package com.example.achivia.feature.files.repository;

import com.example.achivia.feature.files.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface FileRepository extends JpaRepository<File, UUID> {

    @Query(value = "SELECT * FROM files WHERE id = :id", nativeQuery = true)
    File findByIdNative(@Param("id") UUID id);

    @Query(value = "SELECT * FROM files WHERE uploader_user_id = :userId", nativeQuery = true)
    List<File> findAllByUploaderUserId(@Param("userId") UUID userId);

    @Query(value = "DELETE FROM files WHERE id = :id", nativeQuery = true)
    void deleteByIdNative(@Param("id") UUID id);

    @Query(value = "SELECT * FROM files", nativeQuery = true)
    List<File> findAllNative();
}
