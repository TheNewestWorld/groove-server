package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.VocEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocRepository extends JpaRepository<VocEntity, Long> {
}
