package com.sajid.Achievia.feature.guilds.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GuildResponseDto {

    private Long id;
    private String name;
    private LocalDateTime createdAt;

}
