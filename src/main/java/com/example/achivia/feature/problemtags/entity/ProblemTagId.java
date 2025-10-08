package com.example.achivia.feature.problemtags.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class ProblemTagId implements java.io.Serializable {
    private UUID problemId;
    private UUID tagId;
}
