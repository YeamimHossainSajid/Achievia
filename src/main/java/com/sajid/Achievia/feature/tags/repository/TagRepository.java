package com.sajid.Achievia.feature.tags.repository;

import com.sajid.Achievia.feature.tags.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
