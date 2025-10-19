package com.example.achivia.feature.blogvote.repository;

import com.example.achivia.feature.blogvote.entity.BlogVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface BlogVoteRepository extends JpaRepository<BlogVote, UUID> {

    @Query(value = "SELECT * FROM blog_votes WHERE blog_id = :blogId AND user_id = :userId LIMIT 1", nativeQuery = true)
    Optional<BlogVote> findByBlogIdAndUserId(@Param("blogId") UUID blogId, @Param("userId") UUID userId);

    @Query(value = "SELECT COUNT(*) FROM blog_votes WHERE blog_id = :blogId AND vote_type = 'UPVOTE'", nativeQuery = true)
    long countUpvotes(@Param("blogId") UUID blogId);

    @Query(value = "SELECT COUNT(*) FROM blog_votes WHERE blog_id = :blogId AND vote_type = 'DOWNVOTE'", nativeQuery = true)
    long countDownvotes(@Param("blogId") UUID blogId);
}
