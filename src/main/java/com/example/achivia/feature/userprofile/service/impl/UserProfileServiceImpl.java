package com.example.achivia.feature.userprofile.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.userprofile.entity.UserProfile;
import com.example.achivia.feature.userprofile.payload.request.UserProfileRequestDto;
import com.example.achivia.feature.userprofile.payload.response.UserProfileResponseDto;
import com.example.achivia.feature.userprofile.repository.UserProfileRepository;
import com.example.achivia.feature.userprofile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepo userRepository;

    @Override
    public UserProfileResponseDto createProfile(UserProfileRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId());

        UserProfile profile = UserProfile.builder()
                .user(user)
                .displayName(requestDto.getDisplayName())
                .avatarUrl(requestDto.getAvatarUrl())
                .bio(requestDto.getBio())
                .countryCode(requestDto.getCountryCode())
                .timezone(requestDto.getTimezone())
                .level(requestDto.getLevel() != null ? requestDto.getLevel() : 1)
                .xp(requestDto.getXp() != null ? requestDto.getXp() : 0L)
                .streakDays(requestDto.getStreakDays() != null ? requestDto.getStreakDays() : 0)
                .build();

        userProfileRepository.save(profile);
        return mapToDto(profile);
    }

    @Override
    public UserProfileResponseDto getProfileByUserId(Long userId) {
        UserProfile profile = userProfileRepository.findByUserIdNative(userId);
        if (profile == null) throw new RuntimeException("User profile not found");
        return mapToDto(profile);
    }

    @Override
    public UserProfileResponseDto updateProfile(Long userId, UserProfileRequestDto requestDto) {
        LocalDateTime now = LocalDateTime.now();
        userProfileRepository.updateUserProfileNative(
                userId,
                requestDto.getDisplayName(),
                requestDto.getAvatarUrl(),
                requestDto.getBio(),
                requestDto.getCountryCode(),
                requestDto.getTimezone(),
                requestDto.getLevel(),
                requestDto.getXp(),
                requestDto.getStreakDays(),
                now
        );
        UserProfile updatedProfile = userProfileRepository.findByUserIdNative(userId);
        return mapToDto(updatedProfile);
    }

    private UserProfileResponseDto mapToDto(UserProfile profile) {
        return UserProfileResponseDto.builder()
                .id(profile.getId())
                .userId(profile.getUser().getId())
                .displayName(profile.getDisplayName())
                .avatarUrl(profile.getAvatarUrl())
                .bio(profile.getBio())
                .countryCode(profile.getCountryCode())
                .timezone(profile.getTimezone())
                .level(profile.getLevel())
                .xp(profile.getXp())
                .streakDays(profile.getStreakDays())
                .lastActiveAt(profile.getLastActiveAt())
                .createdAt(profile.getCreatedAt())
                .updatedAt(profile.getUpdatedAt())
                .build();
    }
}
