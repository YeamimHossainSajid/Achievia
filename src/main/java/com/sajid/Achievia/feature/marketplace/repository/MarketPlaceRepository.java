package com.sajid.Achievia.feature.marketplace.repository;

import com.sajid.Achievia.feature.marketplace.entity.Marketplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketPlaceRepository extends JpaRepository<Marketplace, Long> {
}
