package org.bogus.groove.storage.repository;

import java.util.List;
import org.bogus.groove.storage.entity.UserAuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityRepository extends JpaRepository<UserAuthorityEntity, Long> {
    List<UserAuthorityEntity> findAllByUserId(Long userId);

    void deleteAllByUserId(Long userId);
}
