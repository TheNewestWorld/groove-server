package org.bogus.groove.storage.repository;

import java.util.List;
import org.bogus.groove.storage.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findAllByPostIdAndIsDeletedFalseOrderByCreatedAt(Long postId);

    Integer countByPostIdAndIsDeletedFalse(Long postId);

    List<CommentEntity> findAllByParentIdAndIsDeletedFalseOrderByCreatedAt(Long commentId);
}
