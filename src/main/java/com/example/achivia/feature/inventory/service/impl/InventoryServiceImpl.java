package com.example.achivia.feature.inventory.service.impl;

import com.example.achivia.auth.model.User;
import com.example.achivia.auth.repository.UserRepo;
import com.example.achivia.feature.inventory.entity.Inventory;
import com.example.achivia.feature.inventory.payload.request.InventoryRequestDto;
import com.example.achivia.feature.inventory.payload.response.InventoryResponseDto;
import com.example.achivia.feature.inventory.repository.InventoryRepository;
import com.example.achivia.feature.inventory.service.InventoryService;
import com.example.achivia.feature.shopitem.entity.ShopItem;
import com.example.achivia.feature.shopitem.repository.ShopItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final UserRepo userRepository;
    private final ShopItemRepository shopItemRepository;

    @Override
    public InventoryResponseDto create(InventoryRequestDto request) {
        User user = userRepository.findById(request.getUserId()).get();
        ShopItem shopItem = shopItemRepository.findById(request.getShopItemId())
                .orElseThrow(() -> new RuntimeException("ShopItem not found"));

        Inventory inventory = convertToEntity(request, user, shopItem);
        Inventory saved = inventoryRepository.save(inventory);
        return convertToDto(saved);
    }

    @Override
    public InventoryResponseDto getById(UUID id) {
        Inventory inventory = inventoryRepository.findByIdNative(id);
        if (inventory == null) throw new RuntimeException("Inventory not found");
        return convertToDto(inventory);
    }

    @Override
    public List<InventoryResponseDto> getAll() {
        return inventoryRepository.findAllNative()
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<InventoryResponseDto> getByUserId(Long userId) {
        return inventoryRepository.findByUserId(userId)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<InventoryResponseDto> getByShopItemId(UUID shopItemId) {
        return inventoryRepository.findByShopItemId(shopItemId)
                .stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public InventoryResponseDto update(UUID id, InventoryRequestDto request) {
        Inventory updated = inventoryRepository.updateInventory(id, request.getQuantity());
        return convertToDto(updated);
    }

    @Override
    public void delete(UUID id) {
        inventoryRepository.deleteByIdNative(id);
    }


    private Inventory convertToEntity(InventoryRequestDto dto, User user, ShopItem shopItem) {
        return Inventory.builder()
                .user(user)
                .shopItem(shopItem)
                .quantity(dto.getQuantity() != null ? dto.getQuantity() : 1)
                .build();
    }

    private InventoryResponseDto convertToDto(Inventory entity) {
        InventoryResponseDto dto = new InventoryResponseDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setShopItemId(entity.getShopItem().getId());
        dto.setQuantity(entity.getQuantity());
        dto.setAcquiredAt(entity.getAcquiredAt());
        return dto;
    }
}
