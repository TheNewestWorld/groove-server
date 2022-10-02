package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {}
