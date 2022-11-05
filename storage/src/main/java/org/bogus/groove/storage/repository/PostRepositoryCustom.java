package org.bogus.groove.storage.repository;

import org.bogus.groove.storage.entity.PostEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface PostRepositoryCustom {
    Slice<PostEntity> findAllPosts(Long categoryId, Pageable pageable, String word);

    Slice<PostEntity> findAllLikedPosts(Long userId, Pageable pageable);
}
