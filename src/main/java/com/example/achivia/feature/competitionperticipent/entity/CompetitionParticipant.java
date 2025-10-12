package com.example.achivia.feature.competitionperticipent.entity;

import com.example.achivia.auth.model.User;
import com.example.achivia.feature.competition.entity.Competition;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "competition_participants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetitionParticipant {
    @EmbeddedId
    private CompetitionParticipantId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("competitionId")
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "registered_at", nullable = false, updatable = false)
    private LocalDateTime registeredAt;

    @Column(name = "total_score", nullable = false)
    @Builder.Default
    private Integer totalScore = 0;
}
