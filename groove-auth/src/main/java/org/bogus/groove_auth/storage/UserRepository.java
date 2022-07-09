package org.bogus.groove_auth.storage;

import java.util.Optional;
import org.bogus.groove.common.enumeration.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndType(String email, UserType type);
}
