package org.bogus.groove.storage.repository;

import java.util.Optional;
import org.bogus.groove.storage.entity.MailSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MailSessionRepository extends JpaRepository<MailSessionEntity, Long> {
    Optional<MailSessionEntity> findBySessionKey(String sessionKey);
}
