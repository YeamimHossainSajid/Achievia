package com.example.achivia.feature.userstats.entity;


import com.example.achivia.feature.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "user_stats")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStats {
    @Id
    @Column(name = "user_id")
    private UUID userId;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "total_points", nullable = false)
    @Builder.Default
    private Long totalPoints = 0L;

    @Column(name = "total_submissions", nullable = false)
    @Builder.Default
    private Integer totalSubmissions = 0;

    @Column(name = "solved_problems", nullable = false)
    @Builder.Default
    private Integer solvedProblems = 0;

    @Column(name = "competition_points", nullable = false)
    @Builder.Default
    private Long competitionPoints = 0L;

    @Column(name = "habit_points", nullable = false)
    @Builder.Default
    private Long habitPoints = 0L;

    @Column(name = "last_recalculated_at", nullable = false)
    @Builder.Default
    private LocalDateTime lastRecalculatedAt = LocalDateTime.now();
}