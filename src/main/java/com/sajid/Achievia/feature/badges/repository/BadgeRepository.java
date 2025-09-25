package com.sajid.Achievia.feature.badges.repository;

import com.sajid.Achievia.feature.badges.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

}
