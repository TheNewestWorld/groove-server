package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.CoachingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoachingRepository extends JpaRepository<CoachingEntity, Long> {
    
}