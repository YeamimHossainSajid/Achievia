package com.example.achivia.feature.purchase.service;

import com.example.achivia.feature.purchase.payload.request.PurchaseRequestDto;
import com.example.achivia.feature.purchase.payload.response.PurchaseResponseDto;

import java.util.List;
import java.util.UUID;

public interface PurchaseService {
    PurchaseResponseDto createPurchase(PurchaseRequestDto requestDto);
    List<PurchaseResponseDto> getPurchasesByUser(Long userId);
    PurchaseResponseDto getPurchaseById(UUID id);
}
