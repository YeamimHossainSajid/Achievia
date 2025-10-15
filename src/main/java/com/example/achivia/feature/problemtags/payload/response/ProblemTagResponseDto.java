package com.example.achivia.feature.problemtags.payload.response;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemTagResponseDto {
    private UUID problemId;
    private UUID tagId;
    private String problemTitle;
    private String tagName;
}
