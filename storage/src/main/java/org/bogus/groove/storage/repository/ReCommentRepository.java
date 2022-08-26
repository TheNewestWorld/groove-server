package org.bogus.groove.storage.repository;

import java.util.List;
import org.bogus.groove.storage.entity.ReCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReCommentRepository extends JpaRepository<ReCommentEntity, Long> {
    List<ReCommentEntity> findAllByCommentId(Long commentId);
}
