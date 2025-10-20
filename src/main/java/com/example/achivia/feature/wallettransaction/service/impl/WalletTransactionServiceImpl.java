package com.example.achivia.feature.wallettransaction.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.wallettransaction.entity.WalletTransaction;
import com.example.achivia.feature.wallettransaction.payload.request.WalletTransactionRequestDto;
import com.example.achivia.feature.wallettransaction.payload.response.WalletTransactionResponseDto;
import com.example.achivia.feature.wallettransaction.repository.WalletTransactionRepository;
import com.example.achivia.feature.wallettransaction.service.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository repository;
    private final UserRepo userRepository;

    @Override
    public WalletTransactionResponseDto createTransaction(WalletTransactionRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId()).orElse(null);

        WalletTransaction transaction = WalletTransaction.builder()
                .user(user)
                .amount(requestDto.getAmount())
                .reason(requestDto.getReason())
                .referenceId(requestDto.getReferenceId())
                .metadata(requestDto.getMetadata())
                .build();

        repository.save(transaction);
        return mapToDto(transaction);
    }

    @Override
    public List<WalletTransactionResponseDto> getTransactionsByUser(Long userId) {
        List<WalletTransaction> transactions = repository.findByUserId(userId);
        return transactions.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public WalletTransactionResponseDto getTransactionById(UUID id) {
        WalletTransaction transaction = repository.findById(id).orElse(null);
        if (transaction == null) throw new RuntimeException("Transaction not found");
        return mapToDto(transaction);
    }

    private WalletTransactionResponseDto mapToDto(WalletTransaction transaction) {
        return WalletTransactionResponseDto.builder()
                .id(transaction.getId())
                .userId(transaction.getUser().getId())
                .amount(transaction.getAmount())
                .reason(transaction.getReason())
                .referenceId(transaction.getReferenceId())
                .metadata(transaction.getMetadata())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
