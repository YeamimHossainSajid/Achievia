package com.example.achivia.feature.shopitem.controller;

import com.example.achivia.feature.shopitem.payload.request.ShopItemRequestDto;
import com.example.achivia.feature.shopitem.payload.response.ShopItemResponseDto;
import com.example.achivia.feature.shopitem.service.ShopItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/shop-items")
@RequiredArgsConstructor
public class ShopItemController {

    private final ShopItemService service;

    @PostMapping
    public ResponseEntity<ShopItemResponseDto> createShopItem(@RequestBody ShopItemRequestDto requestDto) {
        return ResponseEntity.ok(service.createShopItem(requestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopItemResponseDto> getShopItemById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getShopItemById(id));
    }

    @GetMapping
    public ResponseEntity<List<ShopItemResponseDto>> getAllShopItems() {
        return ResponseEntity.ok(service.getAllShopItems());
    }
}
