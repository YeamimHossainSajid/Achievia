package com.example.achivia.feature.problemtags.payload.request;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemTagRequestDto {
    private UUID problemId;
    private UUID tagId;
}
