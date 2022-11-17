package org.bogus.groove.storage;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailAuthenticationRepository extends JpaRepository<EmailAuthenticationEntity, Long> {
    Optional<EmailAuthenticationEntity> findBySessionKey(String sessionKey);
}
