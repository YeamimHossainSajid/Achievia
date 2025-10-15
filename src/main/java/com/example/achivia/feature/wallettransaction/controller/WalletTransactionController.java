package com.example.achivia.feature.wallettransaction.controller;

import com.example.achivia.feature.wallettransaction.payload.request.WalletTransactionRequestDto;
import com.example.achivia.feature.wallettransaction.payload.response.WalletTransactionResponseDto;
import com.example.achivia.feature.wallettransaction.service.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/wallet-transactions")
@RequiredArgsConstructor
public class WalletTransactionController {

    private final WalletTransactionService service;

    @PostMapping
    public ResponseEntity<WalletTransactionResponseDto> createTransaction(@RequestBody WalletTransactionRequestDto requestDto) {
        return ResponseEntity.ok(service.createTransaction(requestDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WalletTransactionResponseDto>> getTransactionsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getTransactionsByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WalletTransactionResponseDto> getTransactionById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getTransactionById(id));
    }
}
