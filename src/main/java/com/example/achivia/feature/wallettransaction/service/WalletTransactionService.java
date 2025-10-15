package com.example.achivia.feature.wallettransaction.service;

import com.example.achivia.feature.wallettransaction.payload.request.WalletTransactionRequestDto;
import com.example.achivia.feature.wallettransaction.payload.response.WalletTransactionResponseDto;

import java.util.List;
import java.util.UUID;

public interface WalletTransactionService {
    WalletTransactionResponseDto createTransaction(WalletTransactionRequestDto requestDto);
    List<WalletTransactionResponseDto> getTransactionsByUser(Long userId);
    WalletTransactionResponseDto getTransactionById(UUID id);
}
