package com.example.achivia.feature.shopitem.service.impl;

import com.example.achivia.feature.shopitem.entity.ShopItem;
import com.example.achivia.feature.shopitem.payload.request.ShopItemRequestDto;
import com.example.achivia.feature.shopitem.payload.response.ShopItemResponseDto;
import com.example.achivia.feature.shopitem.repository.ShopItemRepository;
import com.example.achivia.feature.shopitem.service.ShopItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopItemServiceImpl implements ShopItemService {

    private final ShopItemRepository repository;

    @Override
    public ShopItemResponseDto createShopItem(ShopItemRequestDto requestDto) {
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        repository.insertShopItem(
                id,
                requestDto.getSlug(),
                requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getIconUrl(),
                requestDto.getPrice(),
                requestDto.getIsActive() != null ? requestDto.getIsActive() : true,
                now,
                now
        );

        ShopItem shopItem = repository.findByIdNative(id)
                .orElseThrow(() -> new RuntimeException("Failed to fetch inserted ShopItem"));

        return mapToDto(shopItem);
    }

    @Override
    public ShopItemResponseDto getShopItemById(UUID id) {
        ShopItem shopItem = repository.findByIdNative(id)
                .orElseThrow(() -> new RuntimeException("ShopItem not found"));
        return mapToDto(shopItem);
    }

    @Override
    public List<ShopItemResponseDto> getAllShopItems() {
        List<ShopItem> items = repository.findAllNative();
        return items.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private ShopItemResponseDto mapToDto(ShopItem shopItem) {
        return ShopItemResponseDto.builder()
                .id(shopItem.getId())
                .slug(shopItem.getSlug())
                .name(shopItem.getName())
                .description(shopItem.getDescription())
                .iconUrl(shopItem.getIconUrl())
                .price(shopItem.getPrice())
                .isActive(shopItem.getIsActive())
                .createdAt(shopItem.getCreatedAt())
                .updatedAt(shopItem.getUpdatedAt())
                .build();
    }
}
