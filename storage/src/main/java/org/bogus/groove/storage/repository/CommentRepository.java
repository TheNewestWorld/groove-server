package org.bogus.groove.storage.repository;

import java.util.List;
import org.bogus.groove.storage.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByPostIdAndIsDeletedFalse(Long postId);

    Integer countByPostIdAndIsDeletedFalse(Long postId);

    List<CommentEntity> findAllByParentIdAndIsDeletedFalse(Long commentId);
}
