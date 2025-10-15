package com.example.achivia.feature.inventory.controller;

import com.example.achivia.feature.inventory.payload.request.InventoryRequestDto;
import com.example.achivia.feature.inventory.payload.response.InventoryResponseDto;
import com.example.achivia.feature.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryResponseDto> create(@RequestBody InventoryRequestDto request) {
        return ResponseEntity.ok(inventoryService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponseDto> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(inventoryService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<InventoryResponseDto>> getAll() {
        return ResponseEntity.ok(inventoryService.getAll());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InventoryResponseDto>> getByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(inventoryService.getByUserId(userId));
    }

    @GetMapping("/shop-item/{shopItemId}")
    public ResponseEntity<List<InventoryResponseDto>> getByShopItemId(@PathVariable UUID shopItemId) {
        return ResponseEntity.ok(inventoryService.getByShopItemId(shopItemId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryResponseDto> update(@PathVariable UUID id, @RequestBody InventoryRequestDto request) {
        return ResponseEntity.ok(inventoryService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        inventoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
