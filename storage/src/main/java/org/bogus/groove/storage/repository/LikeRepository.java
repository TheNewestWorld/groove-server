package org.bogus.groove.storage.repository;

import java.util.List;
import java.util.Optional;
import org.bogus.groove.storage.entity.LikeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
    List<LikeEntity> findByUserId(Long userId);

    Optional<LikeEntity> findByUserIdAndPostId(Long userId, Long postId);

    Integer countByPostId(Long postId);

    Slice<LikeEntity> findAllByUserId(Long userId, Pageable pageable);
}
