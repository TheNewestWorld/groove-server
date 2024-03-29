package org.bogus.groove.storage.repository;

import java.util.Optional;
import org.bogus.groove.common.enumeration.ProviderType;
import org.bogus.groove.storage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndProviderTypeAndActiveIsTrue(String email, ProviderType type);

    Optional<UserEntity> findByIdAndActiveIsTrue(Long userId);

    boolean existsByNickname(String nickname);

    boolean existsByEmailAndProviderType(String email, ProviderType type);
}
