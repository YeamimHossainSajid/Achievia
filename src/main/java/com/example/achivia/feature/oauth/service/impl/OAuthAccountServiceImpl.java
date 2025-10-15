package com.example.achivia.feature.oauth.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.oauth.entity.OAuthAccount;
import com.example.achivia.feature.oauth.payload.request.OAuthAccountRequestDto;
import com.example.achivia.feature.oauth.payload.response.OAuthAccountResponseDto;
import com.example.achivia.feature.oauth.repository.OAuthAccountRepository;
import com.example.achivia.feature.oauth.service.OAuthAccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OAuthAccountServiceImpl implements OAuthAccountService {

    private final OAuthAccountRepository oAuthAccountRepository;
    private final UserRepo userRepository;

    @Override
    public OAuthAccountResponseDto createOAuthAccount(OAuthAccountRequestDto dto) {
        User user = userRepository.findById(dto.getUserId());

        OAuthAccount account = OAuthAccount.builder()
                .user(user)
                .provider(dto.getProvider())
                .providerAccountId(dto.getProviderAccountId())
                .accessToken(dto.getAccessToken())
                .refreshToken(dto.getRefreshToken())
                .expiresAt(dto.getExpiresAt())
                .build();

        OAuthAccount saved = oAuthAccountRepository.save(account);

        return mapToResponse(saved);
    }

    @Override
    public List<OAuthAccountResponseDto> getAccountsByUser(UUID userId) {
        return oAuthAccountRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OAuthAccountResponseDto getAccountByProvider(String provider, String providerAccountId) {
        OAuthAccount account = oAuthAccountRepository.findByProviderAndProviderAccountId(provider, providerAccountId)
                .orElseThrow(() -> new EntityNotFoundException("OAuth account not found for provider: " + provider));
        return mapToResponse(account);
    }

    private OAuthAccountResponseDto mapToResponse(OAuthAccount account) {
        return OAuthAccountResponseDto.builder()
                .id(account.getId())
                .userId(account.getUser().getId())
                .provider(account.getProvider())
                .providerAccountId(account.getProviderAccountId())
                .accessToken(account.getAccessToken())
                .refreshToken(account.getRefreshToken())
                .expiresAt(account.getExpiresAt())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
