package com.sajid.Achievia.feature.marketplace.service.impl;

import com.sajid.Achievia.feature.marketplace.entity.Marketplace;
import com.sajid.Achievia.feature.marketplace.payload.request.MarketPlaceRequestDto;
import com.sajid.Achievia.feature.marketplace.payload.response.MarketPlaceResponseDto;
import com.sajid.Achievia.feature.marketplace.repository.MarketPlaceRepository;
import com.sajid.Achievia.feature.marketplace.service.MarketPlaceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MarketPlaceServiceImpl implements MarketPlaceService {

    private final MarketPlaceRepository marketPlaceRepository;

    public MarketPlaceServiceImpl(MarketPlaceRepository marketPlaceRepository) {
        this.marketPlaceRepository = marketPlaceRepository;
    }

    private MarketPlaceResponseDto convertToDto(Marketplace marketplace) {
        MarketPlaceResponseDto marketPlaceResponseDto = new MarketPlaceResponseDto();
        marketPlaceResponseDto.setId(marketplace.getId());
        marketPlaceResponseDto.setShopName(marketplace.getShopName());
        return marketPlaceResponseDto;
    }

    private Marketplace convertToEntity(MarketPlaceRequestDto marketPlaceRequestDto) {
        Marketplace marketplace = new Marketplace();
        marketplace.setShopName(marketPlaceRequestDto.getShopName());
        return marketplace;
    }

    @Override
    public void create(MarketPlaceRequestDto marketPlaceRequestDto) {
        Marketplace marketplace = convertToEntity(marketPlaceRequestDto);
        marketPlaceRepository.save(marketplace);
    }

    @Override
    public void delete(Long id) {
        Marketplace marketplace = marketPlaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marketplace not found with id: " + id));
        marketPlaceRepository.delete(marketplace);
    }

    @Override
    public void update(Long id, MarketPlaceRequestDto marketPlaceRequestDto) {
        Marketplace marketplace = marketPlaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marketplace not found with id: " + id));
        marketplace.setShopName(marketPlaceRequestDto.getShopName());
        marketPlaceRepository.save(marketplace);
    }

    @Override
    public MarketPlaceResponseDto get(Long id) {
        Marketplace marketplace = marketPlaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marketplace not found with id: " + id));
        return convertToDto(marketplace);
    }

    @Override
    public List<MarketPlaceResponseDto> getAll() {
        return marketPlaceRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
}

