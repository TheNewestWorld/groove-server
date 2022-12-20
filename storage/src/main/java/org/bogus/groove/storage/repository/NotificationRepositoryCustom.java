package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.NotificationEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface NotificationRepositoryCustom {
    Slice<NotificationEntity> findAllNotifications(Long userId, Pageable pageable);
}