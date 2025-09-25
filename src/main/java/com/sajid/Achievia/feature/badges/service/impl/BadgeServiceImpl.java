package com.sajid.Achievia.feature.badges.service.impl;

import com.sajid.Achievia.feature.badges.entity.Badge;
import com.sajid.Achievia.feature.badges.payload.request.BadgeRequestDto;
import com.sajid.Achievia.feature.badges.payload.response.BadgeResponseDto;
import com.sajid.Achievia.feature.badges.repository.BadgeRepository;
import com.sajid.Achievia.feature.badges.service.BadgeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepository badgeRepository;

    public BadgeServiceImpl(BadgeRepository badgeRepository) {
        this.badgeRepository = badgeRepository;
    }

    private Badge convertToBadge(BadgeRequestDto badgeRequestDto) {
        Badge badge = new Badge();
        badge.setBadgeName(badgeRequestDto.getBadgeName());
        return badge;
    }

    private BadgeResponseDto convertToBadgeResponseDto(Badge badge) {
        BadgeResponseDto badgeResponseDto = new BadgeResponseDto();
        badgeResponseDto.setId(badge.getId());
        badgeResponseDto.setBadgeName(badge.getBadgeName());
        return badgeResponseDto;
    }

    @Override
    public void create(BadgeRequestDto badgeRequestDto) {
        Badge badge = convertToBadge(badgeRequestDto);
        badgeRepository.save(badge);
    }

    @Override
    public void delete(Long id) {
        if (!badgeRepository.existsById(id)) {
            throw new RuntimeException("Badge not found with id: " + id);
        }
        badgeRepository.deleteById(id);
    }

    @Override
    public void update(BadgeRequestDto badgeRequestDto, Long id) {
        Badge badge = badgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Badge not found with id: " + id));
        badge.setBadgeName(badgeRequestDto.getBadgeName());
        badgeRepository.save(badge);
    }

    @Override
    public BadgeResponseDto get(Long id) {
        Badge badge = badgeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Badge not found with id: " + id));
        return convertToBadgeResponseDto(badge);
    }

    @Override
    public List<BadgeResponseDto> getAll() {
        return badgeRepository.findAll()
                .stream()
                .map(this::convertToBadgeResponseDto)
                .collect(Collectors.toList());
    }
}

