package com.example.achivia.feature.userprofile.service;

import com.example.achivia.feature.userprofile.payload.request.UserProfileRequestDto;
import com.example.achivia.feature.userprofile.payload.response.UserProfileResponseDto;

public interface UserProfileService {
    UserProfileResponseDto createProfile(UserProfileRequestDto requestDto);
    UserProfileResponseDto getProfileByUserId(Long userId);
    UserProfileResponseDto updateProfile(Long userId, UserProfileRequestDto requestDto);
}
