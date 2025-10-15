package com.example.achivia.feature.tag.payload.response;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagResponseDto {
    private UUID id;
    private String name;
}
