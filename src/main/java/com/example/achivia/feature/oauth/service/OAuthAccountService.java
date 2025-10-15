package com.example.achivia.feature.oauth.service;

import com.example.achivia.feature.oauth.payload.request.OAuthAccountRequestDto;
import com.example.achivia.feature.oauth.payload.response.OAuthAccountResponseDto;

import java.util.List;
import java.util.UUID;

public interface OAuthAccountService {
    OAuthAccountResponseDto createOAuthAccount(OAuthAccountRequestDto dto);
    List<OAuthAccountResponseDto> getAccountsByUser(UUID userId);
    OAuthAccountResponseDto getAccountByProvider(String provider, String providerAccountId);
}
