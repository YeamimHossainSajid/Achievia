package com.example.achivia.feature.problemtest.payload.request;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProblemTestRequestDto {
    private UUID problemId;
    private Boolean isSample;
    private String input;
    private String expectedOutput;
    private Integer scoreWeight;
}
