package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.SomethingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SomethingRepository extends JpaRepository<SomethingEntity, Long> {
}
