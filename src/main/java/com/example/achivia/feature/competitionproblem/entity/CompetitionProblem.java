package com.example.achivia.feature.competitionproblem.entity;

import com.example.achivia.feature.competition.entity.Competition;
import com.example.achivia.feature.problem.entity.Problem;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "competition_problems")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompetitionProblem {
    @EmbeddedId
    private CompetitionProblemId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("competitionId")
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("problemId")
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Column(name = "order_index", nullable = false)
    @Builder.Default
    private Integer orderIndex = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer points = 100;
}

