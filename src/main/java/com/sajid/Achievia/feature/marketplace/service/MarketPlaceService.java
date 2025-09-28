package com.sajid.Achievia.feature.marketplace.service;

import com.sajid.Achievia.feature.marketplace.payload.request.MarketPlaceRequestDto;
import com.sajid.Achievia.feature.marketplace.payload.response.MarketPlaceResponseDto;

import java.util.List;

public interface MarketPlaceService {

    public void create(MarketPlaceRequestDto marketPlaceRequestDto);
    public void delete(Long id);
    public void update(Long id,MarketPlaceRequestDto marketPlaceRequestDto);
    public MarketPlaceResponseDto get(Long id);
    public List<MarketPlaceResponseDto> getAll();
}
