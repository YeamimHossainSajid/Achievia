package com.example.achivia.feature.userwallet.controller;

import com.example.achivia.feature.userwallet.payload.request.UserWalletRequestDto;
import com.example.achivia.feature.userwallet.payload.response.UserWalletResponseDto;
import com.example.achivia.feature.userwallet.service.UserWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-wallet")
@RequiredArgsConstructor
public class UserWalletController {

    private final UserWalletService service;

    @PostMapping
    public ResponseEntity<UserWalletResponseDto> createWallet(@RequestBody UserWalletRequestDto requestDto) {
        return ResponseEntity.ok(service.createWallet(requestDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserWalletResponseDto> getWallet(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getWalletByUserId(userId));
    }

    @PutMapping("/user/{userId}/balance")
    public ResponseEntity<UserWalletResponseDto> updateBalance(@PathVariable Long userId,
                                                               @RequestParam Long balance) {
        return ResponseEntity.ok(service.updateWalletBalance(userId, balance));
    }
}
