package com.sajid.Achievia.feature.marketplace.controller;

import com.sajid.Achievia.feature.marketplace.payload.request.MarketPlaceRequestDto;
import com.sajid.Achievia.feature.marketplace.payload.response.MarketPlaceResponseDto;
import com.sajid.Achievia.feature.marketplace.service.MarketPlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marketplaces")
public class MarketPlaceController {

    private final MarketPlaceService marketPlaceService;

    public MarketPlaceController(MarketPlaceService marketPlaceService) {
        this.marketPlaceService = marketPlaceService;
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody MarketPlaceRequestDto requestDto) {
        marketPlaceService.create(requestDto);
        return ResponseEntity.ok("Marketplace created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody MarketPlaceRequestDto requestDto) {
        marketPlaceService.update(id, requestDto);
        return ResponseEntity.ok("Marketplace updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        marketPlaceService.delete(id);
        return ResponseEntity.ok("Marketplace deleted successfully!");
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarketPlaceResponseDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(marketPlaceService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<MarketPlaceResponseDto>> getAll() {
        return ResponseEntity.ok(marketPlaceService.getAll());
    }
}

