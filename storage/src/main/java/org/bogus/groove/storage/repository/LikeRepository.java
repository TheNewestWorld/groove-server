package org.bogus.groove.storage.repository;

import java.util.List;
import java.util.Optional;
import org.bogus.groove.storage.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    List<LikeEntity> findByUserId(Long userId);

    Optional<LikeEntity> findByUserIdAndPostId(Long userId, Long postId);

    Integer countByPostId(Long postId);

    boolean existsByUserIdAndPostId(Long userId, Long postId);
}
