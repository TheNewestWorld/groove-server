package org.bogus.groove.storage.repository;

import java.util.Optional;
import org.bogus.groove.common.enumeration.ProviderType;
import org.bogus.groove.storage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndProviderType(String email, ProviderType providerType);

    Optional<UserEntity> findByNickname(String nickname);
}
