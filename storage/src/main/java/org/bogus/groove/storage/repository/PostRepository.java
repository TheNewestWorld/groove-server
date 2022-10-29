package org.bogus.groove.storage.repository;

import java.util.Optional;
import org.bogus.groove.storage.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long>, PostRepositoryCustom {
    Optional<PostEntity> findByIdAndIsDeletedFalse(Long postId);
}
