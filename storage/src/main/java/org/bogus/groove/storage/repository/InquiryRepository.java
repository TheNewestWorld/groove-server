package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.InquiryEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<InquiryEntity, Long> {
    Slice<InquiryEntity> findAllByUserId(Long userId, Pageable pageable);
}
