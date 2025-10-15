package com.example.achivia.feature.problemtest.payload.response;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemTestResponseDto {
    private UUID id;
    private UUID problemId;
    private String problemTitle;
    private Boolean isSample;
    private String input;
    private String expectedOutput;
    private Integer scoreWeight;
}
