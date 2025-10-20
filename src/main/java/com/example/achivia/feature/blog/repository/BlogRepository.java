package com.example.achivia.feature.blog.repository;

import com.example.achivia.feature.blog.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BlogRepository extends JpaRepository<Blog, UUID> {

    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO blog
        (id, author_id, title, slug, content, cover_image_url, is_published, created_at, updated_at)
        VALUES (:id, :authorId, :title, :slug, :content, :coverImageUrl, :isPublished, :createdAt, :updatedAt)
        """, nativeQuery = true)
    void save(UUID id, Long authorId, String title, String slug, String content, String coverImageUrl, Boolean isPublished, LocalDateTime createdAt, LocalDateTime updatedAt);


    @Query(value = """
        SELECT * FROM blog
        WHERE id = :id
        """, nativeQuery = true)
    Blog findByIdNative(UUID id);


    @Query(value = """
        SELECT * FROM blog
        WHERE slug = :slug
        """, nativeQuery = true)
    Blog findBySlugNative(String slug);


    @Query(value = """
        SELECT * FROM blog
        WHERE author_id = :authorId
        """, nativeQuery = true)
    List<Blog> findByAuthorIdNative(Long authorId);


    @Modifying
    @Transactional
    @Query(value = """
        UPDATE blog
        SET title = :title,
            slug = :slug,
            content = :content,
            cover_image_url = :coverImageUrl,
            is_published = :isPublished,
            updated_at = :updatedAt
        WHERE id = :id
        """, nativeQuery = true)
    void update(UUID id, String title, String slug, String content, String coverImageUrl, Boolean isPublished, LocalDateTime updatedAt);


    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM blog
        WHERE id = :id
        """, nativeQuery = true)
    void delete(UUID id);
}
