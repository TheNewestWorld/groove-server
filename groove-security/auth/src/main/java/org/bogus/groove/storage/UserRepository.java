package org.bogus.groove.storage;

import java.util.Optional;
import org.bogus.groove.domain.user.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmailAndTypeAndActiveIsTrue(String email, UserType type);

    Optional<UserEntity> findByIdAndActiveIsTrue(Long userId);

    boolean existsByNickname(String nickname);

    boolean existsByEmailAndType(String email, UserType type);
}
