package com.example.achivia.feature.userprofile.controller;

import com.example.achivia.feature.userprofile.payload.request.UserProfileRequestDto;
import com.example.achivia.feature.userprofile.payload.response.UserProfileResponseDto;
import com.example.achivia.feature.userprofile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService service;

    @PostMapping
    public ResponseEntity<UserProfileResponseDto> createProfile(@RequestBody UserProfileRequestDto requestDto) {
        return ResponseEntity.ok(service.createProfile(requestDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfileResponseDto> getProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getProfileByUserId(userId));
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserProfileResponseDto> updateProfile(@PathVariable Long userId,
                                                                @RequestBody UserProfileRequestDto requestDto) {
        return ResponseEntity.ok(service.updateProfile(userId, requestDto));
    }
}
