package com.example.achivia.feature.userwallet.service;

import com.example.achivia.feature.userwallet.payload.request.UserWalletRequestDto;
import com.example.achivia.feature.userwallet.payload.response.UserWalletResponseDto;

public interface UserWalletService {
    UserWalletResponseDto createWallet(UserWalletRequestDto requestDto);
    UserWalletResponseDto getWalletByUserId(Long userId);
    UserWalletResponseDto updateWalletBalance(Long userId, Long balance);
}
