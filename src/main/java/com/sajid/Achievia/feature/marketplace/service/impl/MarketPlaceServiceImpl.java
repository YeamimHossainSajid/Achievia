package com.sajid.Achievia.feature.marketplace.service.impl;

import com.sajid.Achievia.feature.marketplace.repository.MarketPlaceRepository;
import com.sajid.Achievia.feature.marketplace.service.MarketPlaceService;
import org.springframework.stereotype.Service;

@Service
public class MarketPlaceServiceImpl implements MarketPlaceService {

    private MarketPlaceRepository marketPlaceRepository;

    public MarketPlaceServiceImpl(MarketPlaceRepository marketPlaceRepository) {
        this.marketPlaceRepository = marketPlaceRepository;
    }
}
