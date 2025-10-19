package com.example.achivia.feature.blog.repository;

import com.example.achivia.feature.blog.entity.Blog;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BlogRepository extends JpaRepository<Blog, UUID> {

    @Query(value = "SELECT * FROM blogs WHERE author_user_id = :authorId", nativeQuery = true)
    List<Blog> findByAuthorIdNative(@Param("authorId") Long authorId);

    @Query(value = "SELECT * FROM blogs WHERE id = :id", nativeQuery = true)
    Blog findByIdNative(@Param("id") UUID id);

    @Query(value = "SELECT * FROM blogs WHERE slug = :slug", nativeQuery = true)
    Blog findBySlugNative(@Param("slug") String slug);
}

