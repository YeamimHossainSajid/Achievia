package com.example.achivia.feature.blogvote.repository;

import com.example.achivia.feature.blogvote.entity.BlogVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BlogVoteRepository extends JpaRepository<BlogVote, UUID> {

    @Query(value = """
            SELECT *
            FROM blog_votes
            WHERE blog_id = :blogId
              AND user_id = :userId
            """, nativeQuery = true)
    Optional<BlogVote> findByBlogIdAndUserId(@Param("blogId") UUID blogId,
                                             @Param("userId") UUID userId);

    @Query(value = """
            SELECT COUNT(*)
            FROM blog_votes
            WHERE blog_id = :blogId
              AND vote_type = 'UPVOTE'
            """, nativeQuery = true)
    long countUpvotes(@Param("blogId") UUID blogId);

    @Query(value = """
            SELECT COUNT(*)
            FROM blog_votes
            WHERE blog_id = :blogId
              AND vote_type = 'DOWNVOTE'
            """, nativeQuery = true)
    long countDownvotes(@Param("blogId") UUID blogId);

    @Modifying
    @Query(value = """
            INSERT INTO blog_votes (id, blog_id, user_id, vote_type, created_at)
            VALUES (:id, :blogId, :userId, :voteType, NOW())
            """, nativeQuery = true)
    void save(@Param("id") UUID id,
                    @Param("blogId") UUID blogId,
                    @Param("userId") UUID userId,
                    @Param("voteType") String voteType);

    @Modifying
    @Query(value = """
            UPDATE blog_votes
            SET vote_type = :voteType,
                created_at = NOW()
            WHERE blog_id = :blogId
              AND user_id = :userId
            """, nativeQuery = true)
    void update(@Param("blogId") UUID blogId,
                    @Param("userId") UUID userId,
                    @Param("voteType") String voteType);
}
