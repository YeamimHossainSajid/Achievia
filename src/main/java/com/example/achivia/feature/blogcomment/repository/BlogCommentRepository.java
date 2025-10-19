package com.example.achivia.feature.blogcomment.repository;

import com.example.achivia.feature.blogcomment.entity.BlogComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface BlogCommentRepository extends JpaRepository<BlogComment, UUID> {

    @Query(value = "SELECT * FROM blog_comments WHERE blog_id = :blogId AND parent_comment_id IS NULL ORDER BY created_at ASC", nativeQuery = true)
    List<BlogComment> findTopLevelComments(@Param("blogId") UUID blogId);

    @Query(value = "SELECT * FROM blog_comments WHERE parent_comment_id = :parentId ORDER BY created_at ASC", nativeQuery = true)
    List<BlogComment> findRepliesByParentId(@Param("parentId") UUID parentId);
}
