package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long>, NotificationRepositoryCustom {
}
