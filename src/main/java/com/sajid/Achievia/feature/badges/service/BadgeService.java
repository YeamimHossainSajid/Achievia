package com.sajid.Achievia.feature.badges.service;

import com.sajid.Achievia.feature.badges.entity.Badge;
import com.sajid.Achievia.feature.badges.payload.request.BadgeRequestDto;
import com.sajid.Achievia.feature.badges.payload.response.BadgeResponseDto;

import java.util.List;

public interface BadgeService {
    public void create(BadgeRequestDto badgeRequestDto);
    public void delete(Long id);
    public void update(BadgeRequestDto badgeRequestDto,Long id);
    public BadgeResponseDto get(Long id);
    public List<BadgeResponseDto> getAll();
}
