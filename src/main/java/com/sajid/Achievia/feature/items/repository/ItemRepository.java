package com.sajid.Achievia.feature.items.repository;

import com.sajid.Achievia.feature.items.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}
