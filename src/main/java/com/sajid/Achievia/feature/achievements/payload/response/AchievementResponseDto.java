package com.sajid.Achievia.feature.achievements.payload.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AchievementResponseDto {

    private Long id;
    private String achievementName;
    private String description;

}
