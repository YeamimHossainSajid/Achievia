package com.example.achivia.feature.inventory.service;

import com.example.achivia.feature.inventory.payload.request.InventoryRequestDto;
import com.example.achivia.feature.inventory.payload.response.InventoryResponseDto;

import java.util.List;
import java.util.UUID;

public interface InventoryService {
    InventoryResponseDto create(InventoryRequestDto request);
    InventoryResponseDto getById(UUID id);
    List<InventoryResponseDto> getAll();
    List<InventoryResponseDto> getByUserId(Long userId);
    List<InventoryResponseDto> getByShopItemId(UUID shopItemId);
    InventoryResponseDto update(UUID id, InventoryRequestDto request);
    void delete(UUID id);
}
