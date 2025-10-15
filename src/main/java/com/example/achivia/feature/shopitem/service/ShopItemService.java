package com.example.achivia.feature.shopitem.service;
import com.example.achivia.feature.shopitem.payload.request.ShopItemRequestDto;
import com.example.achivia.feature.shopitem.payload.response.ShopItemResponseDto;

import java.util.List;
import java.util.UUID;

public interface ShopItemService {
    ShopItemResponseDto createShopItem(ShopItemRequestDto requestDto);
    ShopItemResponseDto getShopItemById(UUID id);
    List<ShopItemResponseDto> getAllShopItems();
}
