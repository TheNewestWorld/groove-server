package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.NoticeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    Slice<NoticeEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

}
