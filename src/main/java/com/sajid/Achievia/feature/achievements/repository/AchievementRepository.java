package com.sajid.Achievia.feature.achievements.repository;

import com.sajid.Achievia.feature.achievements.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {

}
