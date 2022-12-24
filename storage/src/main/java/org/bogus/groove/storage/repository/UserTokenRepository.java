package org.bogus.groove.storage.repository;

import java.util.Optional;
import org.bogus.groove.storage.entity.UserTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTokenRepository extends JpaRepository<UserTokenEntity, Long> {
    Optional<UserTokenEntity> findByUserId(Long userId);
}