package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.RecordEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<RecordEntity, Long> {
    Slice<RecordEntity> findAllByUserIdAndDeletedIsFalse(long userId, Pageable pageable);
}
