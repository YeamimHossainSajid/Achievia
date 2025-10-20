package com.example.achivia.feature.blogcomment.repository;

import com.example.achivia.feature.blogcomment.entity.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BlogCommentRepository extends JpaRepository<BlogComment, UUID> {

    @Query(value = """
            SELECT *
            FROM blog_comments
            WHERE blog_id = :blogId
              AND parent_comment_id IS NULL
            ORDER BY created_at ASC
            """, nativeQuery = true)
    List<BlogComment> findTopLevelComments(@Param("blogId") UUID blogId);

    @Query(value = """
            SELECT *
            FROM blog_comments
            WHERE parent_comment_id = :parentId
            ORDER BY created_at ASC
            """, nativeQuery = true)
    List<BlogComment> findRepliesByParentId(@Param("parentId") UUID parentId);

    @Modifying
    @Query(value = """
            INSERT INTO blog_comments (id, blog_id, user_id, content, parent_comment_id, created_at, updated_at)
            VALUES (:id, :blogId, :userId, :content, :parentCommentId, NOW(), NOW())
            """, nativeQuery = true)
    void save(@Param("id") UUID id,
                       @Param("blogId") UUID blogId,
                       @Param("userId") UUID userId,
                       @Param("content") String content,
                       @Param("parentCommentId") UUID parentCommentId);

    @Modifying
    @Query(value = """
            DELETE FROM blog_comments
            WHERE id = :id
            """, nativeQuery = true)
    void delete(@Param("id") UUID id);
}

