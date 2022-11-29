package org.bogus.groove.storage.repository;

import java.util.Optional;
import org.bogus.groove.common.enumeration.UserType;
import org.bogus.groove.storage.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndType(String email, UserType type);

    Optional<UserEntity> findByNickname(String nickname);
}
