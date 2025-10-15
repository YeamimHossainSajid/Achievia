package com.example.achivia.feature.userwallet.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.userwallet.entity.UserWallet;
import com.example.achivia.feature.userwallet.payload.request.UserWalletRequestDto;
import com.example.achivia.feature.userwallet.payload.response.UserWalletResponseDto;
import com.example.achivia.feature.userwallet.repository.UserWalletRepository;
import com.example.achivia.feature.userwallet.service.UserWalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class UserWalletServiceImpl implements UserWalletService {

    private final UserWalletRepository userWalletRepository;
    private final UserRepo userRepository;

    @Override
    public UserWalletResponseDto createWallet(UserWalletRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserWallet wallet = UserWallet.builder()
                .user(user)
                .balance(requestDto.getBalance() != null ? requestDto.getBalance() : 0L)
                .build();

        userWalletRepository.save(wallet);
        return mapToDto(wallet);
    }

    @Override
    public UserWalletResponseDto getWalletByUserId(Long userId) {
        UserWallet wallet = userWalletRepository.findByUserIdNative(userId);
        if (wallet == null) throw new RuntimeException("Wallet not found");
        return mapToDto(wallet);
    }

    @Override
    public UserWalletResponseDto updateWalletBalance(Long userId, Long balance) {
        LocalDateTime now = LocalDateTime.now();
        userWalletRepository.updateBalanceNative(userId, balance, now);
        UserWallet updatedWallet = userWalletRepository.findByUserIdNative(userId);
        return mapToDto(updatedWallet);
    }

    private UserWalletResponseDto mapToDto(UserWallet wallet) {
        return UserWalletResponseDto.builder()
                .userId(wallet.getUser().getId())
                .balance(wallet.getBalance())
                .updatedAt(wallet.getUpdatedAt())
                .build();
    }
}
