package org.bogus.groove.storage.repository;

import java.util.Optional;
import org.bogus.groove.storage.entity.EmailAuthenticationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAuthenticationRepository extends JpaRepository<EmailAuthenticationEntity, Long> {
    Optional<EmailAuthenticationEntity> findBySessionKey(String sessionKey);
}
