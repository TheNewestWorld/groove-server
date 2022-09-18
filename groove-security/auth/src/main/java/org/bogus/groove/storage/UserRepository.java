package org.bogus.groove.storage;

import java.util.Optional;
import org.bogus.groove.domain.user.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndType(String email, UserType type);
}
