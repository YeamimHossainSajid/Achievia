package com.sajid.Achievia.feature.guilds.repository;

import com.sajid.Achievia.feature.guilds.entity.Guild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuildRepository extends JpaRepository<Guild, Long> {

}
