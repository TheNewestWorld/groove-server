package org.bogus.groove_auth.storage;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepository extends JpaRepository<UserAuthorityEntity, Long> {
    List<UserAuthorityEntity> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);
}
