package com.example.achivia.feature.problemtags.entity;

import com.example.achivia.feature.problem.entity.Problem;
import com.example.achivia.feature.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "problem_tags")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemTag {
    @EmbeddedId
    private ProblemTagId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("problemId")
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    @JoinColumn(name = "tag_id")
    private Tag tag;
}


