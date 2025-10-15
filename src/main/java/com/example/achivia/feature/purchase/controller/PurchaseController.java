package com.example.achivia.feature.purchase.controller;

import com.example.achivia.feature.purchase.payload.request.PurchaseRequestDto;
import com.example.achivia.feature.purchase.payload.response.PurchaseResponseDto;
import com.example.achivia.feature.purchase.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseResponseDto> createPurchase(@RequestBody PurchaseRequestDto requestDto) {
        return ResponseEntity.ok(purchaseService.createPurchase(requestDto));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PurchaseResponseDto>> getPurchasesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(purchaseService.getPurchasesByUser(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponseDto> getPurchaseById(@PathVariable UUID id) {
        return ResponseEntity.ok(purchaseService.getPurchaseById(id));
    }
}
