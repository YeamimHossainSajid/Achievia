package com.sajid.Achievia.feature.guilds.payload.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GuildRequestDto {

    private String name;
    private LocalDateTime createdAt;

}
