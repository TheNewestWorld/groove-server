package org.bogus.groove.storage.repository;

import org.bogus.groove.common.enumeration.SortOrderType;
import org.bogus.groove.storage.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PostRepositoryCustom {
    Page<PostEntity> findAllPosts(Long categoryId, PageRequest of, SortOrderType sortOrderType, String word);
}
