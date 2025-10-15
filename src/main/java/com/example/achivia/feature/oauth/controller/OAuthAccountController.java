package com.example.achivia.feature.oauth.controller;

import com.example.achivia.feature.oauth.payload.request.OAuthAccountRequestDto;
import com.example.achivia.feature.oauth.payload.response.OAuthAccountResponseDto;
import com.example.achivia.feature.oauth.service.OAuthAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/oauth-accounts")
@RequiredArgsConstructor
public class OAuthAccountController {

    private final OAuthAccountService oAuthAccountService;

    @PostMapping
    public ResponseEntity<OAuthAccountResponseDto> createOAuthAccount(@RequestBody OAuthAccountRequestDto dto) {
        return ResponseEntity.ok(oAuthAccountService.createOAuthAccount(dto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OAuthAccountResponseDto>> getAccountsByUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(oAuthAccountService.getAccountsByUser(userId));
    }

    @GetMapping
    public ResponseEntity<OAuthAccountResponseDto> getAccountByProvider(
            @RequestParam String provider,
            @RequestParam String providerAccountId
    ) {
        return ResponseEntity.ok(oAuthAccountService.getAccountByProvider(provider, providerAccountId));
    }
}
