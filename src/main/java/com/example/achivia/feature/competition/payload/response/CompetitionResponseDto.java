package com.example.achivia.feature.competition.payload.response;


import java.time.LocalDateTime;
import java.util.UUID;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionResponseDto {

    UUID id;
    String slug;
    String name;
    String description;
    LocalDateTime startAt;
    LocalDateTime endAt;
    String visibility;

    UUID hostUserId;
    String hostUserName;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
