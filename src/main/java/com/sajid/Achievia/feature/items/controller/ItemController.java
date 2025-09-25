package com.sajid.Achievia.feature.items.controller;

import com.sajid.Achievia.feature.items.payload.request.ItemRequestDto;
import com.sajid.Achievia.feature.items.payload.response.ItemResponseDto;
import com.sajid.Achievia.feature.items.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getItems() {
        return ResponseEntity.ok(itemService.getItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> getItem(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItem(id));
    }

    @PostMapping
    public ResponseEntity<String> createItem(@RequestBody ItemRequestDto requestDto) {
        itemService.createItem(requestDto);
        return ResponseEntity.ok("Item created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody ItemRequestDto requestDto) {
        itemService.updateItem(id, requestDto);
        return ResponseEntity.ok("Item updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok("Item deleted successfully!");
    }
}

