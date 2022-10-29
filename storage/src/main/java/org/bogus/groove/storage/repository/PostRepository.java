package org.bogus.groove.storage.repository;

import java.util.Optional;
import org.bogus.groove.common.enumeration.SortOrderType;
import org.bogus.groove.storage.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findByIdAndIsDeletedFalse(Long postId);

    Page<PostEntity> findAllByIsDeletedFalseOrderByCreatedAtDesc(Pageable pageable);

    Page<PostEntity> findByCategoryIdAndIsDeletedFalseOrderByCreatedAtDesc(Long categoryId, Pageable pageable);

    Page<PostEntity> findAll(Long categoryId, PageRequest of, SortOrderType sortOrderType, String word);
}
