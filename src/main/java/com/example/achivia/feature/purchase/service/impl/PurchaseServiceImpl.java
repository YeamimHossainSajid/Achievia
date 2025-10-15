package com.example.achivia.feature.purchase.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.purchase.entity.Purchase;
import com.example.achivia.feature.purchase.payload.request.PurchaseRequestDto;
import com.example.achivia.feature.purchase.payload.response.PurchaseResponseDto;
import com.example.achivia.feature.purchase.repository.PurchaseRepository;
import com.example.achivia.feature.purchase.service.PurchaseService;
import com.example.achivia.feature.shopitem.entity.ShopItem;
import com.example.achivia.feature.shopitem.repository.ShopItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final UserRepo userRepository;
    private final ShopItemRepository shopItemRepository;

    @Override
    public PurchaseResponseDto createPurchase(PurchaseRequestDto requestDto) {
        User user = userRepository.findById(requestDto.getUserId());
        ShopItem shopItem = shopItemRepository.findById(requestDto.getShopItemId())
                .orElseThrow(() -> new RuntimeException("Shop item not found"));

        Purchase purchase = Purchase.builder()
                .user(user)
                .shopItem(shopItem)
                .pricePaid(requestDto.getPricePaid())
                .build();

        Purchase saved = purchaseRepository.save(purchase);
        return convertToDto(saved);
    }

    @Override
    public List<PurchaseResponseDto> getPurchasesByUser(Long userId) {
        return purchaseRepository.findPurchasesByUserId(userId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseResponseDto getPurchaseById(UUID id) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));
        return convertToDto(purchase);
    }


    private PurchaseResponseDto convertToDto(Purchase purchase) {
        return PurchaseResponseDto.builder()
                .id(purchase.getId())
                .userId(purchase.getUser().getId())
                .userName(purchase.getUser().getUsername())
                .shopItemId(purchase.getShopItem().getId())
                .shopItemName(purchase.getShopItem().getName())
                .pricePaid(purchase.getPricePaid())
                .createdAt(purchase.getCreatedAt())
                .build();
    }
}
