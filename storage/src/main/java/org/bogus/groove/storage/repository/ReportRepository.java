package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity, Long> {
}
